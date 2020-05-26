package tla.domain.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tla.domain.Util;

public class SentenceTest {

    @Test
    void deserialize() throws Exception {
        SentenceDto s = (SentenceDto) Util.loadFromFile("sentence", "IBUBd3xeVnQoNUbMi4MAnDRUenw.json");
        assertAll("check deserialized properties",
            () -> assertNotNull(s, "sentence should not be null"),
            () -> assertEquals("HS", s.getType(), "type"),
            () -> assertNotNull(s.getContext(), "context"),
            () -> assertNotNull(s.getContext().getParagraph(), "paragraph"),
            () -> assertEquals(117, s.getContext().getPos(), "position in sentences array"),
            () -> assertNotNull(s.getTranslations(), "sentence translations")
        );
        SentenceDto s2 = new SentenceDto();
        s2.setContext(s.getContext());
        s2.setId(s.getId());
        s2.setType(s.getType());
        s2.setTranscription(s.getTranscription());
        s2.setTranslations(s.getTranslations());
        s2.setTokens(s.getTokens());
        assertAll("assert equality",
            () -> assertEquals(s, s2, "instance"),
            () -> assertEquals(s.hashCode(), s2.hashCode(), "hashcode")
        );
    }
}