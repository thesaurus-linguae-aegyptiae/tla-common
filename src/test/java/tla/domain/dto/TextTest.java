package tla.domain.dto;

import org.junit.jupiter.api.Test;

import tla.domain.model.ObjectReference;
import tla.domain.model.Paths;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;
import java.util.TreeSet;

public class TextTest {

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