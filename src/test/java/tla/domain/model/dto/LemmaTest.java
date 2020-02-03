package tla.domain.model.dto;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import tla.domain.model.EditorInfo;
import tla.domain.model.ExternalReference;
import tla.domain.model.Language;
import tla.domain.model.Passport;

import static org.junit.jupiter.api.Assertions.*;

public class LemmaTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void builder() {
        LemmaDto l = LemmaDto.builder()
            .translation(Language.DE, List.of("value1"))
            .translation(Language.DE, List.of("value2"))
            .build();
        assertAll("translations should be built correctly",
            () -> assertEquals(1, l.getTranslations().size(), "exactly 1 translation language should be provided"),
            () -> assertEquals(1, l.getTranslations().get(Language.DE).size(), "exactly 1 german translation should be accessible")
        );
    }

    private LemmaDto loadFromFile(String filename) throws Exception {
        return mapper.readValue(
            new File(
                String.format("src/test/resources/sample/lemma/%s", filename)
            ),
            LemmaDto.class
        );
    }

    @Test
    void deserializeFromFile() throws Exception {
        LemmaDto l = loadFromFile("10070.json");
        assertTrue(l != null, "deserialized lemma DTO should not be null");
        assertTrue(l.getSortString() != null, "lemma sort string should not be null");
    }

    @Test
    void deserializeFromFile_testEditors() throws Exception {
        LemmaDto l = loadFromFile("10070.json");

        assertTrue(l.getEditors() != null, "editor info should not be null");
        LocalDate updated = l.getEditors().getUpdated().toInstant().atZone(ZoneId.of("UTC+01:00")).toLocalDate();
        String year = DateTimeFormatter.ofPattern("yyyy").format(updated);
        assertEquals("2015", year, "latest change should have occured in 2015");
        assertTrue(l.getEditors().getContributors() == null, "there should be no contributing editors");

        EditorInfo e = EditorInfo.builder().author("Altägyptisches Wörterbuch")
            .updated(Date.from(LocalDateTime.of(2015, 6, 26, 16, 14, 4).atZone(ZoneId.systemDefault()).toInstant()))
            .type("user").build();
        assertEquals(e, l.getEditors(), "deserialized should equal procedural build");
    }

    @Test
    void deserializeFromFile_testTranslations() throws Exception {
        LemmaDto l = loadFromFile("10070.json");

        assertTrue(l.getTranslations() != null, "lemma translations field should have non-null value");
        assertEquals(2, l.getTranslations().size(), "exactly 2 translation languages should be present");
        assertEquals(Language.DE, l.getTranslations().firstKey(), "first translation language provided should be german");
        assertTrue(l.getTranslations().containsKey(Language.EN), "english should be in translation languages list");
        assertEquals(1, l.getTranslations().get(Language.DE).size(), "exactly 1 german translation should be present");
        assertTrue(l.getTranslations().get(Language.DE).contains("[Suffix Pron. pl.1.c.]"), "german translations list should contain correct value");
    }

    @Test
    void deserializeFromFile_testPassport() throws Exception {
        LemmaDto l = loadFromFile("10070.json");
        assertTrue(l.getPassport() != null, "lemma passport should not be null");
        Passport p = l.getPassport();
        assertTrue(p.extractPaths().contains("bibliography.bibliographical_text_field"), "passport should contain path to bibl text field");
        assertTrue(p.extractPaths().contains("lemma.main_group.lsort"), "passport should contain path to legacy lsort value");
        Passport leaf = p.getProperties().get("bibliography").get(0).getProperties().get("bibliographical_text_field").get(0);
        List<Passport> values = p.extractProperty("bibliography.bibliographical_text_field");
        assertAll("should be able to extract bibliographical information",
            () -> assertTrue(leaf != null, "passport bibl text field leaf node should not be null"),
            () -> assertEquals("Wb 2, 194; EAG § 159; Schenkel, Einf., 105; ENG § 75; Junge, Näg. Gr., 53", leaf.getLeafNodeValue(), "bibl text value should be correct"),
            () -> assertTrue(values != null, "extracted bibl text field value list should not be null"),
            () -> assertEquals(1, values.size(), "number of bibl text fields should be 1"),
            () -> assertEquals("Wb 2, 194; EAG § 159; Schenkel, Einf., 105; ENG § 75; Junge, Näg. Gr., 53", values.get(0).getLeafNodeValue(), "bibl text value should be correct")
        );
    }

    @Test
    void deserializeFromFile_testExternalReferences() throws Exception {
        LemmaDto l = loadFromFile("10070.json");
        assertAll("should contain external references",
            () -> assertTrue(l.getExternalReferences() != null, "external references should not be null"),
            () -> assertEquals(1, l.getExternalReferences().size(), "should contain exactly 1 references provider"),
            () -> assertTrue(l.getExternalReferences().containsKey("aaew_wcn"), "should contain provider 'aaew_wcn'"),
            () -> assertEquals(1, l.getExternalReferences().get("aaew_wcn").size(), "provider should provide exactly 1 reference")
        );
        ExternalReference e1 = l.getExternalReferences().get("aaew_wcn").get(0);
        ExternalReference e2 = ExternalReference.builder().id("10070").build();
        assertAll("external reference should be as expected",
            () -> assertEquals("10070", e1.getId(), "ref ID should be 10070"),
            () -> assertTrue(e1.getType() == null, "no type should be provided"),
            () -> assertEquals(e2, e1, "deserialized reference should equal procedural build")
        );
    }
}