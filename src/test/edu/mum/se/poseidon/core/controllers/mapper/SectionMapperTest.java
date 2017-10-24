package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.FacultySectionDto;
import edu.mum.se.poseidon.core.repositories.models.Block;
import edu.mum.se.poseidon.core.repositories.models.Course;
import edu.mum.se.poseidon.core.repositories.models.Section;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Yuriy Yugay on 10/19/2017.
 *
 * @author Yuriy Yugay
 */
@RunWith(MockitoJUnitRunner.class)
public class SectionMapperTest {

//    @Mock
    SectionMapper sectionMapper;


    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
        sectionMapper = new SectionMapper();
    }

    @Test
    public void success_map_sectionList_to_facultySectionDtoList_by_sectionId()
            throws Exception {
        long sectionId = 1;

        Section section = new Section();
        section.setId(sectionId);
        section.setBlock(mock(Block.class));
        section.setCourse(mock(Course.class));

        List<Section> sectionList = Arrays.asList(section);

        Map<Long, List<FacultySectionDto>> map = sectionMapper.getFacultySectionDtoMap(sectionList);

        assertThat(map.size(), is(1));
        assertThat(map, hasKey(sectionId));
        assertThat(map.get(sectionId), hasSize(1));
        assertThat(map.get(sectionId).get(0).getId(), is(sectionId));
    }

}