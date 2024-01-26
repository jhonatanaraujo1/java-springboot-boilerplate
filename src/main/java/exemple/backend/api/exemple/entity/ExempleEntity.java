package exemple.backend.api.exemple.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@Table(name = "exemple")
public class ExempleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // You can use UUID instead
    @Column(name = "name")
    private BigDecimal name;

    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
