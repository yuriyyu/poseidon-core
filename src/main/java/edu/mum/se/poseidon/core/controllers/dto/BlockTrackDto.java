package edu.mum.se.poseidon.core.controllers.dto;

import edu.mum.se.poseidon.core.repositories.models.Block;

import java.util.List;

public class BlockTrackDto {

    private BlockDto blockDto;
    private List<SectionDto> sectionDtos;
    private int nStudent;
    private int oStudent;
    private int uStudent;

    // Getter and Setter

    public int getoStudent() {
        return oStudent;
    }

    public void setoStudent(int oStudent) {
        this.oStudent = oStudent;
    }

    public int getuStudent() {
        return uStudent;
    }

    public void setuStudent(int uStudent) {
        this.uStudent = uStudent;
    }

    public int getnStudent() {
        return nStudent;
    }

    public void setnStudent(int nStudent) {
        this.nStudent = nStudent;
    }

    public BlockDto getBlockDto() {
        return blockDto;
    }

    public void setBlockDto(BlockDto blockDto) {
        this.blockDto = blockDto;
    }

    public List<SectionDto> getSectionDtos() {
        return sectionDtos;
    }

    public void setSectionDtos(List<SectionDto> sectionDtos) {
        this.sectionDtos = sectionDtos;
    }
}
