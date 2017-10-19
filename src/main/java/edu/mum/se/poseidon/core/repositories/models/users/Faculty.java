package edu.mum.se.poseidon.core.repositories.models.users;

import edu.mum.se.poseidon.core.repositories.models.Course;
import edu.mum.se.poseidon.core.repositories.models.Section;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Faculty extends User {

	private String academicDegree;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    private List<Section> sections;

	@ManyToMany(mappedBy="faculties")
    private List<Course> courses;
    
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

    public void addSection(Section section) {
    	sections.add(section);
    }
    
    public String getAcademicDegree() {
		return academicDegree;
	}

	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}
}
