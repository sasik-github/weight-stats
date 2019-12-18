package sasik.stats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sasik.stats.domain.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(final String email);
}
