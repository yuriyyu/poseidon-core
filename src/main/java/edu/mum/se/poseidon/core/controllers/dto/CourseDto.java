package edu.mum.se.poseidon.core.controllers.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDto {

	private Long id;
	private String name;
    private Integer number;
    
	private List<PrerequisiteDto> prerequisites = new ArrayList<>();
    private List<FacultyDto> faculties = new ArrayList<>();
    
    public Long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
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

    public List<PrerequisiteDto> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<PrerequisiteDto> prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    public void addPrerequisite(PrerequisiteDto prerequisite) {
    	prerequisites.add(prerequisite);
    }

    public List<FacultyDto> getFaculties(){
    	return faculties;
    }
    
    public void setFaculties(List<FacultyDto> faculties) {
    	this.faculties = faculties;
    }
    
    public void addFaculty(FacultyDto faculty) {
    	faculties.add(faculty);
    }
}
