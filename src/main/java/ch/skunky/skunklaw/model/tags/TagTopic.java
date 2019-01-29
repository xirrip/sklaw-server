package ch.skunky.skunklaw.model.tags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * https://vladmihalcea.com/the-best-way-to-map-a-composite-primary-key-with-jpa-and-hibernate/
 */
@Table(
        uniqueConstraints=@UniqueConstraint(columnNames={"name", "universe"})
)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagTopic {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private String universe;

    private int weight;
}
