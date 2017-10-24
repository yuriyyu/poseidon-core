package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.EntryDto;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class EntryMapperTest {

    EntryMapper entryMapper;

    @Before
    public void setUp() {
        entryMapper = new EntryMapper();
    }

    @Test
    public void success_map_entryList_to_entryDtoList()
            throws Exception {
        long entryId1 = 1;
        long entryId2 = 2;
        long entryId3 = 3;

        List<Entry> entryList = new ArrayList();
        Entry entry1 = new Entry();
        entry1.setId(entryId1);
        Entry entry2 = new Entry();
        entry2.setId(entryId2);
        Entry entry3 = new Entry();
        entry3.setId(entryId3);
        entryList.add(entry1);
        entryList.add(entry2);
        entryList.add(entry3);


        List<EntryDto> entryDtoList = entryMapper.getEntryDtoListFrom(entryList);

        assertThat(entryDtoList.size(), is(3));
        //assertThat(entryDtoList, hasItems(entryMapper.getEntryDtoFrom(entry1)));
        //assertThat(entryDtoList, hasItems(entryMapper.getEntryDtoFrom(entry2)));
        //assertThat(entryDtoList, hasItems(entryMapper.getEntryDtoFrom(entry3)));
    }
}
