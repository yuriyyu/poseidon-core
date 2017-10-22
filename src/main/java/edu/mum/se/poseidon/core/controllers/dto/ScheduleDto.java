package edu.mum.se.poseidon.core.controllers.dto;

import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.repositories.models.Track;
import edu.mum.se.poseidon.core.services.Schedule.BlockTrack;

import java.util.List;
import java.util.Map;

public class ScheduleDto {

    private Long id;
    private String name;
    private int status;
    private List<SectionDto> sections;
    private Map<Track, List<BlockTrackDto>> map;

    public Map<Track, List<BlockTrackDto>> getMap() {
        return map;
    }

    public void setMap(Map<Track, List<BlockTrackDto>> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SectionDto> getSections() {
        return sections;
    }

    public void setSections(List<SectionDto> sections) {
        this.sections = sections;
    }
}
