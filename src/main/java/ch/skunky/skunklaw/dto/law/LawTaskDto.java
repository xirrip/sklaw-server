package ch.skunky.skunklaw.dto.law;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class LawTaskDto {
    private Long id;
    private String type;
    private String description;
    private BigDecimal hourlyCharge;
    private List<WorkLogDto> workLogs;
}
