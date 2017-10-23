package edu.mum.se.poseidon.core.repositories;

import java.util.List;

import edu.mum.se.poseidon.core.controllers.dto.FacultySectionDto;
import jdk.nashorn.internal.runtime.FindProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findAllByDeleted(boolean is_deleted);

    Faculty findByFirstNameEquals(String firstname);

    @Query("select f, c from Faculty f inner  join  f.courses c where c.id = :courseId")
    List<Faculty> findFacultiesByCourseId(@Param("courseId") Long courseId);

}
