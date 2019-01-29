package ch.skunky.skunklaw.dto.law;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WorkLogDto {
    private Long id;

    private String description;

    private Timestamp startTime;
    private Timestamp endTime;

    private long duration;

    private double chargeModifier = 1.0;
}
