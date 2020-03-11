package tla.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}