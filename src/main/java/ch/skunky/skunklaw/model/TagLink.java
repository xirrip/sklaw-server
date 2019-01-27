package ch.skunky.skunklaw.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(
        uniqueConstraints=@UniqueConstraint(columnNames={"topicId1", "topicId2"})
)
@Entity
@Data
@NoArgsConstructor
public class TagLink {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long topicId1;
    private Long topicId2;
    int weight;

    public TagLink(Long topicId1, Long topicId2){
        if(topicId1==topicId2) throw new IllegalArgumentException("Link must be to different topic");
        this.topicId1 = Math.min(topicId1, topicId2);
        this.topicId2 = Math.max(topicId1, topicId2);
        this.weight = 1;
    }

    public void increaseWeight() {
        ++this.weight;
    }
}
