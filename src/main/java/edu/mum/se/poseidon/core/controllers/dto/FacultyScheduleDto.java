package edu.mum.se.poseidon.core.controllers.dto;

import java.util.List;

/**
 * Created by Yuriy Yugay on 10/19/2017.
 *
 * @author Yuriy Yugay
 */
public class FacultyScheduleDto {

    private String entryName;

    private List<FacultySectionDto> facultySectionDtoList;

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public List<FacultySectionDto> getFacultySectionDtoList() {
        return facultySectionDtoList;
    }

    public void setFacultySectionDtoList(List<FacultySectionDto> facultySectionDtoList) {
        this.facultySectionDtoList = facultySectionDtoList;
    }
}
