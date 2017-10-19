package edu.mum.se.poseidon.core.controllers.dto;

/**
 * Created by Yuriy Yugay on 10/18/2017.
 *
 * @author Yuriy Yugay
 */
public class CourseInfoDto {

    private Long id;
    private String name;
    private Integer number;

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
}
