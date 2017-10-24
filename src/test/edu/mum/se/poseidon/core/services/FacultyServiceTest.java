package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.dto.CourseInfoDto;
import edu.mum.se.poseidon.core.controllers.dto.FacultyProfileDto;
import edu.mum.se.poseidon.core.repositories.CourseRepository;
import edu.mum.se.poseidon.core.repositories.FacultyRepository;
import edu.mum.se.poseidon.core.repositories.models.Course;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Yuriy Yugay on 10/23/2017.
 *
 * @author Yuriy Yugay
 */
@RunWith(SpringRunner.class)
public class FacultyServiceTest {

    @TestConfiguration
    public static class TestConfig {

        @MockBean
        FacultyRepository facultyRepository;

        @MockBean
        CourseRepository courseRepository;

        @Bean
        public FacultyService getFacultyService() {
            return new FacultyService(facultyRepository, courseRepository);
        }

        @Bean
        public FacultyRepository getFacultyRepository() {
            return facultyRepository;
        }

        @Bean
        public CourseRepository getCourseRepository() {
            return courseRepository;
        }
    }

    @Autowired
    FacultyService facultyService;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    CourseRepository courseRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = RuntimeException.class)
    public void error_test_update_faculty_profile() throws Exception {
        long facultyId = 1;
        FacultyProfileDto updateDto = mock(FacultyProfileDto.class);

        when(facultyRepository.findOne(facultyId)).thenReturn(null);

        facultyService.updateProfile(facultyId, updateDto);
    }

    @Test
    public void success_test_update_faculty_profile()
            throws Exception {

        long facultyId = 1;
        long newCourseId = 2;

        CourseInfoDto infoDto = mock(CourseInfoDto.class);
        List<CourseInfoDto> infoDtoList = Arrays.asList(infoDto);
        // new faculty profile info dto
        FacultyProfileDto updateDto = new FacultyProfileDto();
        updateDto.setFirstName("John");
        updateDto.setLastName("Smith");
        updateDto.setUsername("jsmith");
        updateDto.setPassword("password");
        updateDto.setCourseList(infoDtoList);

        // new faculty course
        Course newCourse = new Course();
        newCourse.setId(newCourseId);
        Set<Course> newCourseSet = Sets.newSet(newCourse);

        // old faculty's course
        Course oldCourse = new Course();
        oldCourse.setId(33l);
        Set<Course> oldCourseSet = Sets.newSet(oldCourse);
        // old faculty info
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        faculty.setFirstName("Alex");
        faculty.setLastName("Bird");
        faculty.setUsername("abird");
        faculty.setPassword("password");
        faculty.setCourses(oldCourseSet);

        when(facultyRepository.findOne(facultyId)).thenReturn(faculty);
        when(infoDto.getId()).thenReturn(newCourseId);
        when(courseRepository.findByIdIn(Sets.newSet(newCourseId))).thenReturn(newCourseSet);
        when(facultyRepository.save(faculty)).thenReturn(faculty);


        Faculty updated = facultyService.updateProfile(facultyId, updateDto);

        assertThat(updated.getFirstName(), is(updateDto.getFirstName()));
        assertThat(updated.getLastName(), is(updateDto.getLastName()));
        assertThat(updated.getUsername(), is(updateDto.getUsername()));
        assertThat(updated.getPassword(), is(updateDto.getPassword()));
        assertThat(updated.getCourses().size(), is(updateDto.getCourseList().size()));
        assertThat(updated.getCourses().iterator().next().getId(), is(updateDto.getCourseList().iterator().next().getId()));

    }
}