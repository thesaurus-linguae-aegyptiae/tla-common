package tla.domain.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class AnnotationTest {

    private ObjectMapper mapper = new ObjectMapper();

    private AnnotationDto loadFromFile(String filename) throws Exception {
        return mapper.readValue(
            new File(
                String.format("src/test/resources/sample/annotation/%s", filename)
            ),
            AnnotationDto.class
        );
    }

    @Test
    void deserializeFromFile() throws Exception {
        AnnotationDto a = loadFromFile("5YUS7W6LUNC7DLP6SEIQDDKJHE.json");
        assertAll("should be able to deserialize annotation",
            () -> assertTrue(a != null, "should not be null"),
            () -> assertEquals("leipzig_wlist", a.getCorpus(), "corpus value should be set"),
            () -> assertEquals("annotation.lemma", a.getPassport().extractPaths().get(0), "lemma annotation field should exist in passport")
        );

    }
}