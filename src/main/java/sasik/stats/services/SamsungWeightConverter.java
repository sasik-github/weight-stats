package sasik.stats.services;

import org.springframework.stereotype.Service;
import sasik.stats.domain.Measurement;
import sasik.stats.domain.StatItem;
import sasik.stats.domain.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SamsungWeightConverter
{

    public static final String COM_SAMSUNG_HEALTH_WEIGHT = "com.samsung.health.weight";
    public static final String DELIMITER = ",";

    private enum Values
    {
        WEIGHT("weight"),
        TIME("start_time"),
        ;

        private final String value;

        Values(String value) {
            this.value = value;
        }
    }

    public List<StatItem> convert() throws Exception {
        final Path csvFilePath = Paths.get("/Users/sasik/Projects/home/java/prepare_job/stats/com.samsung.health.weight.201910281540.csv");

        BufferedReader csvReader = new BufferedReader(new FileReader(String.valueOf(csvFilePath)));

        if (!csvReader.readLine().startsWith(COM_SAMSUNG_HEALTH_WEIGHT)) {
            throw new Exception("Unsupported file");
        }

        final String headerLine = csvReader.readLine();
        final String[] neededValues = getNeededValues();
        final Map<String, Integer> indexes = makeHeaderIndexes(headerLine, neededValues);
        final List<StatItem> stats = generateStatSet(csvReader, neededValues, indexes);

        stats.sort(Comparator.comparing(StatItem::getDateTime));

        return stats;

    }

    private List<StatItem> generateStatSet(BufferedReader csvReader, String[] neededValues, Map<String, Integer> indexes) throws IOException {
        String line;
        final List<StatItem> stats = new ArrayList<>();
        while ((line = csvReader.readLine()) != null) {
            final String[] values = line.split(DELIMITER);
            final StringBuilder sb = new StringBuilder();

            for (String neededValue : neededValues) {
                sb.append(values[indexes.get(neededValue)])
                        .append("\t");

            }
            sb.append("\n");
            System.out.println(sb);

            //format: 2018-04-18 16:07:38.518
            final String timeStr = values[indexes.get(Values.TIME.value)];
            final LocalDateTime dateTime = LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnn"));
            stats.add(new StatItem(null, dateTime, Double.valueOf(values[indexes.get(Values.WEIGHT.value)]), Measurement.WEIGHT, new User()));
            //@todo I added User relation, need to get default user for converter
            throw new UnsupportedOperationException("there is no valid User entity here");
        }
        return stats;
    }

    private Map<String, Integer> makeHeaderIndexes(String headerLine, String[] neededValues) {
        final Map<String, Integer> indexes = new HashMap<>(neededValues.length);

        final String[] headers = headerLine.split(DELIMITER);
        for (int i = 0; i < headers.length; i++) {
            final String header = headers[i];
            for (String neededValue : neededValues) {
                if (neededValue.equals(header)) {
                    indexes.put(neededValue, i);
                }
            }
        }
        return indexes;
    }

    private String[] getNeededValues() {
        return new String[]{
                Values.WEIGHT.value,
                Values.TIME.value,
                "create_time",
                "update_time",

        };
    }

}
