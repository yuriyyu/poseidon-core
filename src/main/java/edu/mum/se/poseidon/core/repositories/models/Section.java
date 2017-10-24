package edu.mum.se.poseidon.core.repositories.models;

import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import edu.mum.se.poseidon.core.repositories.models.users.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sections")
public class Section
        extends AbstractEntity {

    private Integer maxSeats;
    private String location;

    @ManyToOne
    @JoinColumn(name = "BLOCK_ID", nullable = false)
    private Block block;

    @ManyToOne
    @JoinColumn(name = "FACULTY_ID", nullable = false)
    private Faculty faculty;

    @OneToMany(mappedBy = "id.section", cascade = CascadeType.ALL)
    private List<StudentSection> studentSections;

    @ManyToOne
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<StudentSection> getStudentSections() {
        return studentSections;
    }

    public void setStudentSections(List<StudentSection> studentSections) {
        this.studentSections = studentSections;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return course == null ? "NONE" : course.getName();
    }
}
