package tla.domain.model.dto;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import tla.domain.model.ExternalReference;

import static org.junit.jupiter.api.Assertions.*;

public class ThsEntryTest {

    private ObjectMapper mapper = new ObjectMapper();

    private ThsEntryDto loadFromFile(String filename) throws Exception {
        return mapper.readValue(
            new File(
                String.format("src/test/resources/sample/ths/%s", filename)
            ),
            ThsEntryDto.class
        );
    }

    @Test
    void deserializeFromFile() throws Exception {
        ThsEntryDto t = loadFromFile("2AVEQ3VFT5EEPF7NBH7RHCVBXA.json");
        assertAll("deserialized thesaurus entry and its fields should not be null",
            () -> assertTrue(t != null, "thesaurus entry itself should not be null"),
            () -> assertTrue(t.getEclass() != null, "eclass should not be null"),
            () -> assertEquals("BTSThsEntry", t.getEclass(), "eclass should be 'BTSThsEntry'"),
            () -> assertTrue(t.getEditors() != null, "editor info should not be null"),
            () -> assertTrue(t.getName() != null, "name should not be null"),
            () -> assertTrue(t.getExternalReferences() != null, "eternal references should not be null"),
            () -> assertTrue(t.getType() != null, "type should not be null"),
            () -> assertTrue(t.getRelations() != null, "relations should not be null"),
            () -> assertTrue(t.getId() != null, "id should not be null"),
            () -> assertTrue(t.getReviewState() != null, "review state should not be null"),
            () -> assertEquals(null, t.getSortKey(), "sort key should be null"),
            () -> assertEquals(null, t.getSubtype(), "subtype should be null")
        );
        assertAll("external references should be deserialized correctly",
            () -> assertEquals(2, t.getExternalReferences().size(), "2 providers should be present"),
            () -> assertEquals("aaew_1", t.getExternalReferences().firstKey(), "first provider should be 'aaew_1'"),
            () -> assertEquals(1, t.getExternalReferences().get("thot").size(), "provider 'thot' should specify exactly 1 value")
        );
        assertAll("editor info should be correct",
            () -> assertEquals(1, t.getEditors().getContributors().size(), "exactly 1 contributor should be specified"),
            () -> assertTrue(t.getEditors().getContributors().contains("L. Popko"), "contributor name should be correct"),
            () -> assertEquals("user", t.getEditors().getType(), "author account type should be 'user'")
        );
        assertAll("relations should be correctly deserialized",
            () -> assertEquals(2, t.getRelations().size(), "2 types of relation should be specified"),
            () -> assertEquals(8, t.getRelations().get("contains").size(), "exactly 8 relations of type 'contains' should be specified")
        );
    }


    @Test
    void equality() throws Exception {
        ThsEntryDto t1 = mapper.readValue(
            "{\"id\":\"1\",\"externalReferences\":{\"thot\":[{\"id\":\"ID\",\"type\":\"TYPE\"}]}}",
            ThsEntryDto.class
        );
        ThsEntryDto t2 = ThsEntryDto.builder().id("1")
            .externalReference("thot", List.of(ExternalReference.builder().id("ID").type("TYPE").build()))
            .build();
        assertAll("deserialized thesaurus entry should be equal to procedurally built",
            () -> assertTrue(List.of(t1).contains(t2), "asserting equality"),
            () -> assertEquals(t1, t2, "asserting equality"),
            () -> assertEquals(t1.toString(), t2.toString(), "asserting toString() equality"),
            () -> assertEquals(t1.getId(), t2.getId(), "equal ID"),
            () -> assertEquals(t1.getExternalReferences(), t2.getExternalReferences(), "equal externalreferences")
        );
    }

}
