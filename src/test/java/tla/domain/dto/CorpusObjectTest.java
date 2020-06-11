package tla.domain.dto;

import org.junit.jupiter.api.Test;

import tla.domain.dto.meta.AbstractDto;
import tla.domain.model.EditorInfo;
import tla.domain.util.IO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CorpusObjectTest {

    @Test
    void textcorpusobjectbuilder() {
        CorpusObjectDto o1 = CorpusObjectDto.builder()
            .id("1")
            .name("papyrus")
            .build();
        CorpusObjectDto o2 = CorpusObjectDto.builder()
            .id("1")
            .name("papyrus")
            .editors(null)
            .corpus(null)
            .build();
        assertAll("text corpus object DTO instances created in varying ways should be equal nonetheless",
            () -> assertEquals("BTSTCObject", o1.getEclass(), "eclass must be `BTSTCObject`"),
            () -> assertEquals(o1, o2, "two builder-built instances should be euql"),
            () -> assertEquals(o1.toString(), o2.toString(), "both instances should serialize into same toString() result")
        );
    }

    @Test
    void deserializeSUID() throws Exception {
        CorpusObjectDto o = IO.getMapper().readValue(
            "{\"id\":\"1\",\"hash\":\"xxx\",\"eclass\":\"BTSTCObject\"}",
            CorpusObjectDto.class
        );
        assertNotNull(o);
        assertEquals("xxx", o.getSUID());
        CorpusObjectDto a = (CorpusObjectDto) IO.getMapper().readValue(
            "{\"id\":\"1\",\"hash\":\"xxx\",\"eclass\":\"BTSTCObject\"}",
            AbstractDto.class
        );
        assertEquals("xxx", a.getSUID());
    }

    @Test
    void serializeDeserialize() throws Exception {
        CorpusObjectDto o1 = CorpusObjectDto.builder()
            .id("asdf")
            .editors(
                EditorInfo.builder().author("author").contributors(
                    List.of("collegue")
                ).build())
            .SUID("xxx")
            .type("scene")
            .build();
        o1.getEditors().setDate("created", "2015-12-04");
        o1.getEditors().setDate("updated", "2015-12-05");
        assertDoesNotThrow(
            () -> o1.getEditors().setDate("random", "2015-12-06")
        );
        String s = IO.json(o1);
        assertTrue(s.contains("\"suid\":"), "short id serialized");
        CorpusObjectDto o2 = IO.getMapper().readValue(
            s, CorpusObjectDto.class
        );
        assertAll("instances equal?",
            () -> assertEquals(o1, o2, "instance equal"),
            () -> assertEquals(o1.getSUID(), o2.getSUID(), "short id deserialized"),
            () -> assertTrue(o2.getEditors().getUpdated().after(o2.getEditors().getCreated()), "dates correct")
        );
    }
}
