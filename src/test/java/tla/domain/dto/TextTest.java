package tla.domain.dto;

import org.junit.jupiter.api.Test;

import tla.domain.Util;
import tla.domain.model.ObjectReference;
import tla.domain.model.Passport;
import tla.domain.model.Paths;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TextTest {

    @Test
    void deserializeFromFile() throws Exception {
        TextDto t = (TextDto) Util.loadFromFile("text", "A2HWO5CX6RCQ5IBSV2MHAAIYA4.json");
        assertAll("deserialization from file should preserve all contents",
            () -> assertEquals("tlademotic", t.getCorpus()),
            () -> assertTrue(t.getPaths() != null, "corpus object paths should be deserialized"),
            () -> assertEquals(1, t.getPaths().size(), "expect exactly 1 path"),
            () -> assertEquals(6, t.getPaths().get(0).size(), "count path segments"),
            () -> assertTrue(t.getSentenceIds() != null, "sentence IDs should be preserved"),
            () -> assertEquals(2, t.getSentenceIds().size(), "count sentences")
        );
        Passport pp = t.getPassport();
        List<Passport> leafNodes = pp.extractProperty("object.description_of_object.type");
        assertAll("passport contents should be preserved",
            () -> assertNotNull(pp, "expect passport"),
            () -> assertEquals(1, leafNodes.size(), "expect exactly 1 value for property path `object.description_of_object.type`"),
            () -> assertTrue(leafNodes.get(0).get() instanceof ObjectReference, "leaf node should be thesaurus entry reference"),
            () -> assertEquals("Mumienetikett", ((ObjectReference)leafNodes.get(0).get()).getName(), "check ths ref leaf node name")
        );
    }


    @Test
    void textBuilder() {
        CorpusObjectDto parent = CorpusObjectDto.builder().id("2").name("papyrus").type("type").build();
        ObjectReference r = parent.toObjectReference();
        List<ObjectReference> path = List.of(r);
        Paths paths = Paths.of(List.of(path));
        TextDto t1 = TextDto.builder()
            .id("1")
            .corpus("corpus")
            .paths(paths)
            .build();
        TextDto t2 = TextDto.builder()
            .id("1")
            .corpus("corpus")
            .paths(paths)
            .build();
        assertEquals(t1, t2, "two builder-built instances should be euql");
        assertEquals(t1.toString(), t2.toString(), "both instances should serialize into same toString() result");
        assertEquals(t1.getPaths(), t2.getPaths(), "paths should be the same");
        assertAll("text DTO instances created in varying ways should be equal nonetheless",
            () -> assertNotNull(t1, "instance 1 should not be null"),
            () -> assertNotNull(t2, "instance 2 should not be null")
        );
    }

}