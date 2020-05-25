package tla.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import tla.domain.Util;
import tla.domain.dto.meta.NamedDocumentDto;
import tla.domain.model.ExternalReference;
import tla.domain.model.ObjectReference;
import tla.domain.model.extern.AttestedTimespan;

import static org.junit.jupiter.api.Assertions.*;

public class ThsEntryTest {

    private ObjectMapper mapper = new ObjectMapper();

    public static AttestedTimespan.Period loadThsEntryFromFileAndConvertToTimePeriod(String id) throws Exception {
        NamedDocumentDto term = Util.loadFromFile("ths", String.format("%s.json", id));
        List<Integer> years = new ArrayList<>();
        List.of(
            "thesaurus_date.main_group.beginning",
            "thesaurus_date.main_group.end"
        ).stream().forEach(
            path -> {
                term.getPassport().extractProperty(path).stream().forEach(
                    node -> {
                        if (node.get() instanceof String) {
                            years.add(Integer.valueOf((String) node.get()));
                        }
                    }
                );
            }
        );
        Collections.sort(years);
        return AttestedTimespan.Period.builder()
            .begin(years.get(0))
            .end(years.get(1))
            .ths(ObjectReference.from(term))
            .build();
    }

    @Test
    void deserializeFromFile() throws Exception {
        NamedDocumentDto t = Util.loadFromFile("ths", "2AVEQ3VFT5EEPF7NBH7RHCVBXA.json");
        assertAll("deserialized thesaurus entry and its fields should not be null",
            () -> assertTrue(t != null, "thesaurus entry itself should not be null"),
            () -> assertTrue(t instanceof ThsEntryDto, "should be ths term instance"),
            () -> assertTrue(t.getEclass() != null, "eclass should not be null"),
            () -> assertEquals("BTSThsEntry", t.getEclass(), "eclass should be 'BTSThsEntry'"),
            () -> assertTrue(t.getEditors() != null, "editor info should not be null"),
            () -> assertTrue(t.getName() != null, "name should not be null"),
            () -> assertTrue(t.getExternalReferences() != null, "eternal references should not be null"),
            () -> assertTrue(t.getType() != null, "type should not be null"),
            () -> assertTrue(t.getRelations() != null, "relations should not be null"),
            () -> assertTrue(t.getId() != null, "id should not be null"),
            () -> assertTrue(t.getReviewState() != null, "review state should not be null"),
            () -> assertEquals(null, ((ThsEntryDto)t).getSortKey(), "sort key should be null"),
            () -> assertEquals(null, t.getSubtype(), "subtype should be null"),
            () -> assertEquals("BTSThsEntry", t.getEclass(), "eclass must be `BTSThsEntry`")
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
            "{\"eclass\":\"BTSThsEntry\",\"id\":\"1\",\"externalReferences\":{\"thot\":[{\"id\":\"ID\",\"type\":\"TYPE\"}]}}",
            ThsEntryDto.class
        );
        ThsEntryDto t2 = ThsEntryDto.builder().id("1")
            .externalReference("thot", new TreeSet<>(List.of(ExternalReference.builder().id("ID").type("TYPE").build())))
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
