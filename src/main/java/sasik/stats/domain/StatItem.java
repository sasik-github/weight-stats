package sasik.stats.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "statItems")
@NoArgsConstructor
@AllArgsConstructor
public class StatItem
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dateTime;

    private Double value;

    private Measurement measurement;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}
