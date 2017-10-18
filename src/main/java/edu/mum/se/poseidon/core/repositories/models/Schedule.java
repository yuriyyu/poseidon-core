package edu.mum.se.poseidon.core.repositories.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schedules")
public class Schedule
        extends AbstractEntity {

    private String name;
    private int status;

    @OneToOne
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @OneToMany
    @JoinColumn(name = "SCHEDULE_ID")
    private List<Section> sections;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
