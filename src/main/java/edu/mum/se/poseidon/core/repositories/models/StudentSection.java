package edu.mum.se.poseidon.core.repositories.models;

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

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
}
