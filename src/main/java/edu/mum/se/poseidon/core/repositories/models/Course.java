package edu.mum.se.poseidon.core.repositories.models;

import javax.persistence.*;

import edu.mum.se.poseidon.core.repositories.models.users.Faculty;

import java.util.List;

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
    
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "COURSE_ID")
    private List<Course> prerequisites;
    
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "COURSE_ID")
    private List<Faculty> faculties;
    
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "COURSE_ID")
    private List<Section> sections;
    
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

    public void setSectionList(List<Section> sections) {
        this.sections = sections;
    }
    
    public List<Faculty> getFaculties(){
    	return faculties;
    }
    
    public void setFaculties(List<Faculty> faculties) {
    	this.faculties = faculties;
    }
    
    public void addFaculty(Faculty faculty) {
    	faculties.add(faculty);
    }
}
