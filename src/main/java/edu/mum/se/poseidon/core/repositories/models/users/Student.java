package edu.mum.se.poseidon.core.repositories.models.users;

import edu.mum.se.poseidon.core.repositories.models.Entry;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.repositories.models.StudentSection;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Student extends User {

    private String studentId;
    @ManyToOne
    @JoinColumn(name = "entry_id", nullable = true)
    private Entry entry;
    @OneToMany(mappedBy = "id.student", cascade = CascadeType.ALL)
    private List<StudentSection> studentSections;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public List<StudentSection> getStudentSections() {
        return studentSections;
    }

    public void setStudentSections(List<StudentSection> studentSections) {
        this.studentSections = studentSections;
    }
}
