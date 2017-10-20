package edu.mum.se.poseidon.core.services.Schedule;

import edu.mum.se.poseidon.core.controllers.dto.BlockDto;
import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.repositories.models.Block;
import edu.mum.se.poseidon.core.repositories.models.Section;

import java.util.ArrayList;
import java.util.List;

public class BlockTrack {
    private Block block;
    private int nStudent;
    private List<Section> sections = new ArrayList<Section>();

    /**
     * Creates BlockTrack
     * @param block Block Entity
     * @param nStudent Number of Student in the Block
     */
    public BlockTrack(Block block, int nStudent) {
        this.block = block;
        this.nStudent = nStudent;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public int getnStudent() {
        return nStudent;
    }

    public void setnStudent(int nStudent) {
        this.nStudent = nStudent;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}