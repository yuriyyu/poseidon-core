package edu.mum.se.poseidon.core.repositories.models;

import javax.persistence.*;

import edu.mum.se.poseidon.core.repositories.models.users.Faculty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yuriy Yugay on 10/10/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@Table(name = "courses")
public class Course
        extends AbstractEntity {

    private String name;
    private Integer number;
    
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Course> prerequisites = new ArrayList<>();
    
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Faculty> faculties = new HashSet<>();
    
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "SECTION_ID")
    private List<Section> sections = new ArrayList<>();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    public void addPrerequisite(Course prerequisite) {
    	prerequisites.add(prerequisite);
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
    
    public Set<Faculty> getFaculties(){
    	return faculties;
    }
    
    public void setFaculties(Set<Faculty> faculties) {
    	this.faculties = faculties;
    }
    
    public void addFaculty(Faculty faculty) {
    	faculties.add(faculty);
    }
}
