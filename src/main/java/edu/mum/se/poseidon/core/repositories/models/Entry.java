package edu.mum.se.poseidon.core.repositories.models;

import edu.mum.se.poseidon.core.repositories.models.users.Student;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Yuriy Yugay on 10/10/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@Table(name = "entries")
public class Entry
        extends AbstractEntity {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private Integer nFppStudents;
    private Integer nMppStudents;
    private Integer nFppOpt;
    private Integer nMppOpt;
    private Integer usRes;
    private String name;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entry")
    private List<Block> blockList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entry")
    private List<Student> studentList;

    @OneToOne(cascade = CascadeType.ALL)
    private Schedule schedule;


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getnFppStudents() {
        return nFppStudents;
    }

    public void setnFppStudents(Integer nFppStudents) {
        this.nFppStudents = nFppStudents;
    }

    public Integer getnMppStudents() {
        return nMppStudents;
    }

    public void setnMppStudents(Integer nMppStudents) {
        this.nMppStudents = nMppStudents;
    }

    public Integer getnFppOpt() {
        return nFppOpt;
    }

    public void setnFppOpt(Integer nFppOpt) {
        this.nFppOpt = nFppOpt;
    }

    public Integer getnMppOpt() {
        return nMppOpt;
    }

    public void setnMppOpt(Integer nMppOpt) {
        this.nMppOpt = nMppOpt;
    }

    public Integer getUsRes() {
        return usRes;
    }

    public void setUsRes(Integer usRes) {
        this.usRes = usRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<Block> blockList) {
        this.blockList = blockList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
