package tla.domain.model.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DocumentTest {

    private ObjectMapper mapper = new ObjectMapper();    

    @Test
    void equality() throws Exception {
        DocumentDto d1 = DocumentDto.builder().id("1").build();
        DocumentDto d2 = DocumentDto.builder().id("1").build();
        assertAll("two procedurally build documents should be equal",
            () -> assertEquals(d1, d2, "asserting equality"),
            () -> assertEquals(d1.getId(), d2.getId(), "equal ID")
        );
        DocumentDto d = mapper.readValue(
            "{\"id\":\"1\"}",
            DocumentDto.class
        );
        assertAll("deserialized document should be equal to procedural build",
            () -> assertEquals(d1, d, "asserting equality")
        );
    }

    
}