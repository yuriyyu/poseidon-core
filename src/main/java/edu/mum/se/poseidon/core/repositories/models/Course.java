package edu.mum.se.poseidon.core.repositories.models;

import javax.persistence.*;
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
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "course")
    private List<Course> prerequisites;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "COURSE_ID")
    private List<Section> sectionList;

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

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }
}
