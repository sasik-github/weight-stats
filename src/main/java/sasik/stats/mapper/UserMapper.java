package sasik.stats.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sasik.stats.domain.User;
import sasik.stats.vo.UserRegistrationRequest;

@Service
public class UserMapper
{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User requestToEntity(UserRegistrationRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());

        user.setEnabled(true);
        user.setTokenExpired(false);

        return user;

    }
}
