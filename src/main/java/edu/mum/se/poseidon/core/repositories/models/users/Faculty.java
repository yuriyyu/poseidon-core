package edu.mum.se.poseidon.core.repositories.models.users;

import edu.mum.se.poseidon.core.repositories.models.Section;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Faculty
        extends User {

    private String academicDegree;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    private List<Section> sectionList;

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }
}
