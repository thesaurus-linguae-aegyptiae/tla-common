package tla.domain.dto;

import org.junit.jupiter.api.Test;

import tla.domain.model.ObjectReference;
import tla.domain.model.Passport;
import tla.domain.model.Paths;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TextTest {

    private ObjectMapper mapper = new ObjectMapper();

    private TextDto loadFromFile(String filename) throws Exception {
        return mapper.readValue(
            new File(
                String.format("src/test/resources/sample/text/%s", filename)
            ),
            TextDto.class
        );
    }


    @Test
    void deserializeFromFile() throws Exception {
        TextDto t = loadFromFile("A2HWO5CX6RCQ5IBSV2MHAAIYA4.json");
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
        ObjectReference r = new ObjectReference(
            parent.getId(),
            parent.getEclass(),
            parent.getType(),
            parent.getName()
        );
        Paths paths = new Paths();
        SortedSet<ObjectReference> path = new TreeSet<>();
        path.add(r);
        paths.add(path);
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