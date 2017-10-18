package edu.mum.se.poseidon.core.repositories.models;

import javax.persistence.*;

@Entity
@Table(name="schedules")
public class Schedule
        extends AbstractEntity {

	private String name;
	
	@OneToOne
    @JoinColumn(name = "entry_id")
	private Entry entry;

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
