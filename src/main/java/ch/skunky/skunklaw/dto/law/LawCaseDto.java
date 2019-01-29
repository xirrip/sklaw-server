package ch.skunky.skunklaw.dto.law;

import ch.skunky.skunklaw.model.law.LawTask;
import lombok.Data;

import java.util.List;

@Data
public class LawCaseDto {
    private Long id;
    private String name;
    private String type;
    private String caseId;
    private LawClientDto mainClient;
    private String description;
    private List<LawTask> lawTasks;

}
