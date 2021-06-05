package tla.domain.command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static tla.domain.util.IO.json;

public class AnnotationSearchTest {

    @Test
    @DisplayName("empty type specs should not be serialized")
    void emptyTypeSpecTest() {
        AnnotationSearch cmd = new AnnotationSearch();
        cmd.setType(new TypeSpec(null, null));
        assertFalse(
            json(cmd).contains("\"type\":"), "type field should be absent from serialization"
        );
    }

    @Test
    @DisplayName("specified type and subtype should be serialized")
    void typeSpecTest() {
        AnnotationSearch cmd = new AnnotationSearch();
        cmd.setType(new TypeSpec("lemma", null));
        var s = json(cmd);
        assertAll("type specs should be found in serialization",
            () -> assertTrue(s.contains("\"type\":"), "type field should be present"),
            () -> assertTrue(
                s.lastIndexOf("\"type\":") > s.indexOf("\"type\":"),
                "type element should contain another type field"
            ),
            () -> assertTrue(s.contains("\"lemma\""), "type field value should be present")
        );
    }

    @Test
    @DisplayName("body search term should be serialized only if not empty")
    void bodySearchTerms() {
        AnnotationSearch cmd = new AnnotationSearch();
        assertFalse(json(cmd).contains("\"body\":"), "body field should not be serialized if empty");
        cmd.setBody("quellen");
        assertTrue(json(cmd).contains("\"body\":"), "body field should be serialized if not empty");
    }

}
