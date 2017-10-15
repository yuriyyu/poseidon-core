package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("select s from sections s left join sections_students ss on s.id = ss.sections_id where ss.students_id = :studentId")
    List<Section> findSectionsByStudentId(Long studentId);

}
