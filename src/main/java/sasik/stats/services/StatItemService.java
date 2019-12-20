package sasik.stats.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sasik.stats.domain.Measurement;
import sasik.stats.domain.StatItem;
import sasik.stats.repositories.StatItemRepository;
import sasik.stats.vo.StatItemRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatItemService
{

    private final StatItemRepository statItemRepository;

    public StatItemService(StatItemRepository statItemRepository) {
        this.statItemRepository = statItemRepository;
    }

    public Page<StatItem> findAll(Pageable pageable) {
        final Sort dateTimeSort = Sort.by("dateTime").descending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), dateTimeSort);

        return statItemRepository.findAll(pageable);
    }

    public List<StatItem> findAll() {
        return statItemRepository.findAll();
    }

    public StatItem create(StatItemRequest statItem) {
        final StatItem newStatItem = new StatItem();
        newStatItem.setValue(statItem.getValue());
        LocalDateTime time = statItem.getDateTime();
        if (time == null) {
            time = LocalDateTime.now();
        }
        newStatItem.setDateTime(time);
        newStatItem.setMeasurement(Measurement.WEIGHT);
        newStatItem.setUser(statItem.getUser());
        statItemRepository.save(newStatItem);

        return newStatItem;
    }
}
