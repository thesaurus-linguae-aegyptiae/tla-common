package tla.domain.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import tla.domain.Util;

public class AnnotationTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserializeFromFile() throws Exception {
        AnnotationDto a = (AnnotationDto) Util.loadFromFile(
            Util.SAMPLE_DIR_PATHS.get(AnnotationDto.class),
            "5YUS7W6LUNC7DLP6SEIQDDKJHE.json"
        );
        assertAll("should be able to deserialize annotation",
            () -> assertNotNull(a, "should not be null"),
            () -> assertNotNull(a.getName(), "expect name"),
            () -> assertEquals("leipzig_wlist", a.getCorpus(), "corpus value should be set"),
            () -> assertEquals("annotation.lemma", a.getPassport().extractPaths().get(0), "lemma annotation field should exist in passport"),
            () -> assertEquals("BTSAnnotation", a.getEclass(), "eclass must be `BTSAnnotation`")
        );
    }

    @Test
    void serialize() throws Exception {
        AnnotationDto a = new AnnotationDto();
        a.setCorpus("corpus");
        a.setEclass("BTSAnnotation");
        DocumentDto b = mapper.readValue(
            mapper.writeValueAsString(a),
            DocumentDto.class
        );
        assertAll("test serialize and deserialize again",
            () -> assertEquals(a, b),
            () -> assertTrue(b instanceof AnnotationDto),
            () -> assertEquals(a.hashCode(), b.hashCode()),
            () -> assertEquals(a.toString(), b.toString())
        );
    }
}