package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("select s from Section s left join s.studentList ss where ss.studentId = :studentId")
    List<Section> findSectionsByStudentId(@Param("studentId") Long studentId);

}
