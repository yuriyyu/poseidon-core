package edu.mum.se.poseidon.core.controllers.dto;

import java.util.List;

/**
 * Created by Yuriy Yugay on 10/19/2017.
 *
 * @author Yuriy Yugay
 */
public class StudentScheduleDto {

    private String entryName;

    private List<StudentSectionDto> studentSectionDtoList;

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public List<StudentSectionDto> getStudentSectionDtoList() {
        return studentSectionDtoList;
    }

    public void setStudentSectionDtoList(List<StudentSectionDto> studentSectionDtoList) {
        this.studentSectionDtoList = studentSectionDtoList;
    }
}
