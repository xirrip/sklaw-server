package ch.skunky.skunklaw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(
        uniqueConstraints=@UniqueConstraint(columnNames={"topicId", "itemId"})
)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagItemLink {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long topicId;
    private Long itemId;

    int weight;
}
