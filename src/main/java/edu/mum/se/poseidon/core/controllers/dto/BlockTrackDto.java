package edu.mum.se.poseidon.core.controllers.dto;

import edu.mum.se.poseidon.core.repositories.models.Block;

import java.util.List;

public class BlockTrackDto {

    private BlockDto blockDto;
    private List<SectionDto> sectionDtos;
    private int nStudent;

    // Getter and Setter

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
