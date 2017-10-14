package edu.mum.se.poseidon.core.repositories.models;

import edu.mum.se.poseidon.core.repositories.models.users.Student;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by Yuriy Yugay on 10/10/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@Table(name = "entries")
public class Entry
        extends AbstractEntity {

    private LocalDate startDate;
    private Integer nFppStudents;
    private Integer nMppStudents;
    private Integer nFppOpt;
    private Integer nMppOpt;
    private Integer usRes;
    private String name;

    @OneToMany
    @JoinTable(name = "fk_entries_blocks")
    private Set<Block> blockSet;
    @OneToMany
    @JoinTable(name = "fk_entries_students")
    private Set<Student> studentSet;

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

    public Set<Block> getBlockSet() {
        return blockSet;
    }

    public void setBlockSet(Set<Block> blockSet) {
        this.blockSet = blockSet;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }
}
