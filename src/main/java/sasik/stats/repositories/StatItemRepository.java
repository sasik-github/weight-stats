package sasik.stats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sasik.stats.domain.StatItem;

public interface StatItemRepository extends JpaRepository<StatItem, Long>
{
}
