package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.Schedule;
import edu.mum.se.poseidon.core.repositories.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("select s from Section s join fetch s.studentSections ss where ss.id.student.id = :studentId and ss.isPassed = true")
    List<Section> findSectionsPassedByStudentId(@Param("studentId") Long studentId);

    @Query("select s from Section s join fetch s.studentSections ss where ss.id.student.id = :studentId")
    List<Section> findSectionsByStudentId(@Param("studentId") Long studentId);

    List<Section> findSectionsByDeleted(Boolean deleted);

    List<Section> findBySchedule(Schedule schedule);
}
