package sasik.stats.services;

import org.springframework.stereotype.Service;
import sasik.stats.domain.StatItem;
import sasik.stats.domain.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ChartService
{
    private final StatItemService statItemService;

    public ChartService(StatItemService statItemService) {
        this.statItemService = statItemService;
    }

    public Map<LocalDate, DoubleSummaryStatistics> byMonth(User user) {
        final List<StatItem> stats = statItemService.findAllByUser(user);

        return stats.stream()
            .collect(
                Collectors.groupingBy(
                    statItem -> statItem.getDateTime().toLocalDate().with(TemporalAdjusters.firstDayOfMonth()),
                    TreeMap::new,
                    Collectors.summarizingDouble(StatItem::getValue)
                )
            );
    }

    public Map<LocalDate, DoubleSummaryStatistics> byWeek(User user) {

        return statItemService.findAllByUser(user)
            .stream()
            .collect(
                Collectors.groupingBy(
                    statItem -> statItem.getDateTime().toLocalDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                    TreeMap::new,
                    Collectors.summarizingDouble(StatItem::getValue)
                )
            );
    }

    public Map<LocalDate, DoubleSummaryStatistics> byDay(User user) {

        return statItemService.findAllByUser(user)
            .stream()
            .collect(
                Collectors.groupingBy(
                    statItem -> statItem.getDateTime().toLocalDate(),
                    TreeMap::new,
                    Collectors.summarizingDouble(StatItem::getValue)
                )
            );
    }

}
