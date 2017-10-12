package edu.mum.se.poseidon.core.repositories.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sections")
public class Section
        extends AbstractEntity {

    private Integer maxSeats;
    private String location;

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
}
