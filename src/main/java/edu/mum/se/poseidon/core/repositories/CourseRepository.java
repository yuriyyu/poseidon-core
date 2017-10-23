package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.Course;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {

    //@Query("select c from Course c where c.id = :sectionId")
    //@Query("select s from Section s inner join Course on Course.id = s.course_id where s.id = :sectionId")
    @Query("select c, s from Course c inner  join  c.sections s where s.id = :sectionId")
    Course findCourseBySectionId(@Param("sectionId") Long sectionId);

    List<Course> findAllByDeleted(boolean is_deleted);

    Set<Course> findByIdIn(Set<Long> courseIds);

    Course findCourseByNumber(int number);

}
