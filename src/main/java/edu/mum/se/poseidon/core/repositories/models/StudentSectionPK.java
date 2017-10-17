package edu.mum.se.poseidon.core.repositories.models;

import edu.mum.se.poseidon.core.repositories.models.users.Student;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class StudentSectionPK
        implements Serializable {

//    @Column(name = "STUDENT_ID")
//    private Long student_id;
//
//    @Column(name = "SECTION_ID")
//    private Long section_id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    //    public Long getStudent_id() {
//        return student_id;
//    }
//
//    public void setStudent_id(Long student_id) {
//        this.student_id = student_id;
//    }
//
//    public Long getSection_id() {
//        return section_id;
//    }
//
//    public void setSection_id(Long section_id) {
//        this.section_id = section_id;
//    }
}
