package tla.domain.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tla.domain.Util;

public class AnnotationTest {


    @Test
    void deserializeFromFile() throws Exception {
        AnnotationDto a = (AnnotationDto) Util.loadFromFile(
            Util.SAMPLE_DIR_PATHS.get(AnnotationDto.class),
            "5YUS7W6LUNC7DLP6SEIQDDKJHE.json"
        );
        assertAll("should be able to deserialize annotation",
            () -> assertTrue(a != null, "should not be null"),
            () -> assertEquals("leipzig_wlist", a.getCorpus(), "corpus value should be set"),
            () -> assertEquals("annotation.lemma", a.getPassport().extractPaths().get(0), "lemma annotation field should exist in passport"),
            () -> assertEquals("BTSAnnotation", a.getEclass(), "eclass must be `BTSAnnotation`")
        );

    }
}