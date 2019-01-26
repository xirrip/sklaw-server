package ch.skunky.skunklaw.model;

import lombok.Data;

import javax.persistence.*;

/**
 * https://vladmihalcea.com/the-best-way-to-map-a-composite-primary-key-with-jpa-and-hibernate/
 */
@Table(
        uniqueConstraints=@UniqueConstraint(columnNames={"name", "universe"})
)
@Entity
@Data
public class TagTopic {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private String universe;

    private int weight;
}
