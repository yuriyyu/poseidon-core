package edu.mum.se.poseidon.core.repositories.models;

import edu.mum.se.poseidon.core.repositories.models.users.Student;

import javax.persistence.*;

@Entity
@Table(name = "students_sections")
public class StudentSection {

    @EmbeddedId
    private StudentSectionPK id;

    @Column(name = "is_passed")
    private boolean isPassed;

    public StudentSectionPK getId() {
        return id;
    }

    public void setId(StudentSectionPK id) {
        this.id = id;
    }

//    public Student getStudent() {
//        return student;
//    }

//    public void setStudent(Student student) {
//        this.student = student;
//    }

//    public Section getSection() {
//        return section;
//    }

//    public void setSection(Section section) {
//        this.section = section;
//    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
}
