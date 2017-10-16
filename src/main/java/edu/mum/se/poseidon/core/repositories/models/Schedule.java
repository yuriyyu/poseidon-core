package edu.mum.se.poseidon.core.repositories.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="schedules")
public class Schedule extends AbstractEntity{
	private String name;
	
	@OneToOne(mappedBy = "schedule")
	private Entry entry;
}
