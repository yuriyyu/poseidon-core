package edu.mum.se.poseidon.core.repositories.models.users;

import edu.mum.se.poseidon.core.repositories.models.Entry;
import edu.mum.se.poseidon.core.repositories.models.Section;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Student
        extends User {

    private String studentId;
    @ManyToOne
    @JoinColumn(name = "ENTRY_ID", nullable = false)
    private Entry entry;
    @ManyToMany
    @JoinTable(name = "sections_students")
    private List<Section> sectionList;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }
}
