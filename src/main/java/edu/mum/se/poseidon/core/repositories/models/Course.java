package edu.mum.se.poseidon.core.repositories.models;

import javax.persistence.Entity;
import javax.persistence.Table;

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
