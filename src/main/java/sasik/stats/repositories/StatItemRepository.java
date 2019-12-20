package sasik.stats.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sasik.stats.domain.StatItem;
import sasik.stats.domain.User;

import java.util.List;

public interface StatItemRepository extends JpaRepository<StatItem, Long>
{
    public List<StatItem> findAllByUser(User user);
    public Page<StatItem> findAllByUser(User user, Pageable pageable);

}
