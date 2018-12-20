package ch.skunky.skunklaw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class LawTask {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "law_case_id")
    @JsonIgnore
    private LawCase lawCase;

    private String type;
    private String description;

    private BigDecimal hourlyCharge;

    @OneToMany(
            mappedBy = "lawTask",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WorkLog> workLogs;

    // calendar entry / due date
    // -- warn if work involved

    public void addWorkLog(WorkLog workLog){
        workLogs.add(workLog);
        workLog.setLawTask(this);
    }

    public void removeWorkLog(WorkLog workLog){
        workLogs.remove(workLog);
        workLog.setLawTask(null);
    }


}
