package sasik.stats.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sasik.stats.domain.StatItem;
import sasik.stats.repositories.StatItemRepository;

import java.util.List;

@Service
public class WeightImporter
{
    private final SamsungWeightConverter samsungWeightConverter;
    private final StatItemRepository statItemRepository;

    @Autowired
    public WeightImporter(SamsungWeightConverter samsungWeightConverter, StatItemRepository statItemRepository) {
        this.samsungWeightConverter = samsungWeightConverter;
        this.statItemRepository = statItemRepository;
    }


    public void fillBase() throws Exception {

        final List<StatItem> statItems = samsungWeightConverter.convert();

        statItems.forEach(
                statItemRepository::saveAndFlush
        );

    }
}
