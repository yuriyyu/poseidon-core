package edu.mum.se.poseidon.core.controllers.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy Yugay on 10/18/2017.
 *
 * @author Yuriy Yugay
 */
public class FacultyProfileDto {

	private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String type;
    private String password;
    private List<CourseInfoDto> courseList = new ArrayList<>();

    public List<CourseInfoDto> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseInfoDto> courseList) {
        this.courseList = courseList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
