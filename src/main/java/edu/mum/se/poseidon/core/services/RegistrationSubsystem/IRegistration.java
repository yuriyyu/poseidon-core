package edu.mum.se.poseidon.core.services.RegistrationSubsystem;

import com.sun.javaws.exceptions.InvalidArgumentException;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.repositories.models.users.Student;

import java.util.List;

public interface IRegistration {

    void registerToSection(Long studentId, Long sectionId) throws InvalidArgumentException;

    List<Section> getAvailableSections(Long studentId) throws InvalidArgumentException;

    List<Section> getRegisteredSectionByStudent(Long studentId);
}
