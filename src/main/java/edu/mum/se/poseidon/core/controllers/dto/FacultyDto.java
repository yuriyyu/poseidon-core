package edu.mum.se.poseidon.core.controllers.dto;

import java.util.List;

public class FacultyDto {

	private Long id;
	private String firstName;
    private String lastName;
    private String username;
    private String password;

    private List<SectionDto> sections;
    private List<CourseDto> courses;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public List<CourseDto> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
	}

	public void addCourse(CourseDto course) {
		courses.add(course);
	}
	
	public List<SectionDto> getSections() {
		return sections;
	}

	public void setSections(List<SectionDto> sections) {
		this.sections = sections;
	}

    public void addSection(SectionDto section) {
    	sections.add(section);
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
