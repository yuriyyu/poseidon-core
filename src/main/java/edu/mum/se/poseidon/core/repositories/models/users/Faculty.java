package edu.mum.se.poseidon.core.repositories.models.users;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "faculties")
@PrimaryKeyJoinColumn(name= "faculty_id",referencedColumnName = "id")
public class Faculty
        extends User {

    private String academicDegree;

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }
}
