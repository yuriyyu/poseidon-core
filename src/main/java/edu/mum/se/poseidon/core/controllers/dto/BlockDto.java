package edu.mum.se.poseidon.core.controllers.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import edu.mum.se.poseidon.core.repositories.models.Entry;

public class BlockDto {
	
	private long id;
	private String name;
    private String startDate;
    private String endDate;
	private Entry entry;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
}
