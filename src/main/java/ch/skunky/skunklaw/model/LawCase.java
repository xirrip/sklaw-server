package ch.skunky.skunklaw.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 */

@Entity
@Data
@RequiredArgsConstructor
public class LawCase {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    @Column(unique = true, updatable = false)
    private String caseId;

    @ManyToMany
    private List<Client> clients;

    private String description;

    @OneToMany(
            mappedBy = "lawCase",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<LawTask> lawTasks;

    // document links

    // emails

    public void addLawTask(LawTask lawTask){
        lawTasks.add(lawTask);
        lawTask.setLawCase(this);
    }

    public void removeLawTask(LawTask lawTask){
        lawTasks.remove(lawTask);
        lawTask.setLawCase(null);
    }

}
