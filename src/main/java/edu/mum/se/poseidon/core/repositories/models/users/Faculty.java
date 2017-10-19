package edu.mum.se.poseidon.core.repositories.models.users;

import edu.mum.se.poseidon.core.repositories.models.Course;
import edu.mum.se.poseidon.core.repositories.models.Section;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "faculties")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Faculty extends User {

	private String academicDegree;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    private List<Section> sections = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "faculties_courses",
            joinColumns = @JoinColumn(name = "faculty_id"),
            inverseJoinColumns = @JoinColumn(name="course_id"))
    private Set<Course> courses = new HashSet<>();
    
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses.clear();

		if(courses != null) {
		    this.courses.addAll(courses);
        }
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
