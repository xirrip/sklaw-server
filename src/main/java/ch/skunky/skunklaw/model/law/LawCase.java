package ch.skunky.skunklaw.model.law;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@RequiredArgsConstructor
public class LawCase {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;
    private String type;

    @NotNull
    @Column(unique = true, updatable = false)
    private String caseId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "main_client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LawClient mainClient;

    private String description;

    /*
    @OneToMany(
            mappedBy = "lawCase",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<LawTask> lawTasks;
    */

    // document links

    // emails

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;


    /*
    public void addLawTask(LawTask lawTask){
        lawTasks.add(lawTask);
    }

    public void removeLawTask(LawTask lawTask){
        lawTasks.remove(lawTask);
    }
    */
}
