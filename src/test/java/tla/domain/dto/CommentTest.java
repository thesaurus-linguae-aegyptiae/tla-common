package tla.domain.dto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tla.domain.util.IO.json;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import tla.domain.model.ObjectReference;

public class CommentTest {

    public static CommentDto loadFromFile(String id) throws Exception {
        return tla.domain.util.IO.loadFromFile(
            String.format(
                "src/test/resources/sample/comment/%s.json",
                id
            ),
            CommentDto.class
        );
    }

    @Test
    void deserialize() throws Exception {
        CommentDto c = loadFromFile("IBUBd1ZyuWd4v07BgdanqGACHSU");
        assertAll("test comment deserialization from file",
            () -> assertEquals(c.getEclass(), "BTSComment", "eclass"),
            () -> assertNotNull(c.getBody(), "body"),
            () -> assertNotNull(c.getEditors(), "edit info"),
            () -> assertNotNull(c.getRelations(), "relations"),
            () -> assertEquals("published", c.getReviewState(), "review status")
        );
    }

    @Test
    void equality() throws Exception {
        CommentDto c1 = loadFromFile("IBUBd1ZyuWd4v07BgdanqGACHSU");
        CommentDto c2 = new CommentDto();
        c2.setBody(c1.getBody());
        c2.setEclass("BTSComment");
        c2.setId(c1.getId());
        c2.setReviewState(c1.getReviewState());
        SortedMap<String, SortedSet<ObjectReference>> relations = new TreeMap<>();
        c1.getRelations().entrySet().stream().forEach(
            e -> {
                relations.put(
                    e.getKey(),
                    new TreeSet<>(
                        e.getValue().stream().map(
                            ref -> new ObjectReference(
                                ref.getId(),
                                ref.getEclass(),
                                ref.getType(),
                                ref.getName(),
                                ref.getRanges()
                            )
                        ).collect(
                            Collectors.toList()
                        )
                    )
                );
            }
        );
        c2.setRelations(relations);
        c2.setEditors(c1.getEditors());
        assertAll("deserialized and procedurally built comment instances should equal",
            () -> assertEquals(c1, c2, "whole instance"),
            () -> assertEquals(c1.toString(), c2.toString(), "string repr"),
            () -> assertEquals(c1.hashCode(), c2.hashCode(), "hashcode"),
            () -> assertEquals(
                json(c1),
                json(c2),
                "json serialization"
            )
        );
    }
}