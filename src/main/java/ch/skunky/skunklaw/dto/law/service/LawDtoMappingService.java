package ch.skunky.skunklaw.dto.law.service;

import ch.skunky.skunklaw.dto.law.LawCaseDto;
import ch.skunky.skunklaw.dto.law.LawClientDto;
import ch.skunky.skunklaw.dto.law.LawTaskDto;
import ch.skunky.skunklaw.dto.law.WorkLogDto;
import ch.skunky.skunklaw.model.law.LawCase;
import ch.skunky.skunklaw.model.law.LawClient;
import ch.skunky.skunklaw.model.law.LawTask;
import ch.skunky.skunklaw.model.law.WorkLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LawDtoMappingService {
    private final ModelMapper modelMapper;

    @Autowired
    public LawDtoMappingService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // client
    public LawClientDto toDto(LawClient lawClient){
        LawClientDto dto = modelMapper.map(lawClient, LawClientDto.class);
        return dto;
    }

    public List<LawClientDto> toLawClientsDto(List<LawClient> lawClients){
        return lawClients.stream().map(this::toDto).collect(Collectors.toList());
    }

    public LawClient fromDto(LawClientDto dto){
        LawClient lawClient = modelMapper.map(dto, LawClient.class);
        return lawClient;
    }

    // case
    public LawCaseDto toDto(LawClient lawClient, LawCase lawCase){
        if(lawClient.getId()!=lawCase.getMainClient().getId()){
            throw new IllegalArgumentException("Client is not main client of the case.");
        }
        LawClientDto clientDto = toDto(lawClient);
        LawCaseDto caseDto = modelMapper.map(lawCase, LawCaseDto.class);
        caseDto.setMainClient(clientDto);

        return caseDto;
    }

    public LawCaseDto toDto(Optional<LawClient> lawClient, LawCase lawCase){
        if(lawClient.isPresent()) return toDto(lawClient.get(), lawCase);
        LawCaseDto caseDto = modelMapper.map(lawCase, LawCaseDto.class);
        return caseDto;
    }

    public List<LawCaseDto> toLawCasesDto(LawClient lawClient, List<LawCase> lawCases){
        return lawCases.stream().map(c -> toDto(lawClient, c)).collect(Collectors.toList());
    }

    public LawCase fromDto(LawCaseDto dto){
        LawCase lawCase = modelMapper.map(dto, LawCase.class);
        return lawCase;
    }


    // task
    public LawTaskDto toDto(LawTask lawTask){
        LawTaskDto dto = modelMapper.map(lawTask, LawTaskDto.class);
        return dto;
    }


    public LawTask fromDto(LawTaskDto dto){
        LawTask lawTask = modelMapper.map(dto, LawTask.class);
        return lawTask;
    }

    // work logs
    public WorkLogDto toDto(WorkLog workLog){
        WorkLogDto dto = modelMapper.map(workLog, WorkLogDto.class);
        return dto;
    }

    private List<WorkLogDto> toWorkLogsDto(List<WorkLog> workLogs) {
        return workLogs.stream().map(this::toDto).collect(Collectors.toList());
    }

    public WorkLog fromDto(WorkLogDto dto){
        WorkLog workLog = modelMapper.map(dto, WorkLog.class);
        return workLog;
    }

    public List<WorkLog> fromDto(List<WorkLogDto> workLogs){
        return workLogs.stream().map(this::fromDto).collect(Collectors.toList());
    }

    // optional support
    public Optional<LawClientDto> toClientDto(Optional<LawClient> value){
        if(value.isPresent()) return Optional.of(toDto(value.get()));
        return Optional.empty();
    }
}
