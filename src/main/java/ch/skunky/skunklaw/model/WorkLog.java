package ch.skunky.skunklaw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@RequiredArgsConstructor
public class WorkLog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "law_task_id")
    @JsonIgnore
    private LawTask lawTask;

    private String description;

    private Timestamp startTime;
    private Timestamp endTime;

    private long duration;

    private double chargeModifier = 1.0;
}
