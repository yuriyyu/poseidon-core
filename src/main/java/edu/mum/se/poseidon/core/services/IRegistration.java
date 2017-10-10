package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.repositories.models.Student;

import java.util.List;

public interface IRegistration {

    public void registerToSection(Student student, int sectionId);
    public List<Section> getAvailableSections(Student student);
    public List<Section> getRegisteredSectionByStudent(Student student);
}
