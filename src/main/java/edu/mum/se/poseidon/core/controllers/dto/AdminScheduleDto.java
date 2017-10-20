package edu.mum.se.poseidon.core.controllers.dto;

import java.util.List;

/**
 * Created by Yuriy Yugay on 10/20/2017.
 *
 * @author Yuriy Yugay
 */
public class AdminScheduleDto {

    private String entryName;

    private List<AdminSectionDto> adminSectionDtoList;

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public List<AdminSectionDto> getAdminSectionDtoList() {
        return adminSectionDtoList;
    }

    public void setAdminSectionDtoList(List<AdminSectionDto> adminSectionDtoList) {
        this.adminSectionDtoList = adminSectionDtoList;
    }
}
