package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.id = :sectionId")
    Course findCourseBySectionId(@Param("sectionId") Long sectionId);

    List<Course> findAllByDeleted(boolean is_deleted);
}
