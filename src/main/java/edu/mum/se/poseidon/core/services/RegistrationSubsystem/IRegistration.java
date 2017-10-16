package edu.mum.se.poseidon.core.services.RegistrationSubsystem;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.models.Section;

import java.util.List;

public interface IRegistration {

    void registerToSection(Long studentId, Long sectionId) throws PoseidonException;

    List<Section> getAvailableSections(Long studentId) throws PoseidonException;

    List<Section> getRegisteredSectionByStudent(Long studentId);
}
