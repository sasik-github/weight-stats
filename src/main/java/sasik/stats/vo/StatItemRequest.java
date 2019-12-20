package sasik.stats.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import sasik.stats.domain.User;

import java.time.LocalDateTime;

@Data
public class StatItemRequest
{
    private Double value;

    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateTime = LocalDateTime.now();
}
