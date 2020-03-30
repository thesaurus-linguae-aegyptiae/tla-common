package tla.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DocumentTest {

    private ObjectMapper mapper = new ObjectMapper();    

    @Test
    void equality() throws Exception {
        DocumentDto d1 = LemmaDto.builder().id("1").build();
        DocumentDto d2 = ThsEntryDto.builder().id("1").build();
        assertAll("two procedurally build documents should be equal",
            () -> assertNotEquals(d1, d2, "asserting non-equality"),
            () -> assertEquals(d1.getId(), d2.getId(), "equal ID however")
        );
        DocumentDto d = mapper.readValue(
            "{\"id\":\"1\",\"eclass\":\"BTSLemmaEntry\"}",
            LemmaDto.class
        );
        assertAll("deserialized document should be equal to procedural build",
            () -> assertEquals(d1, d, "asserting equality")
        );
    }

    @Test
    void instantiateWrongEclass() {
        assertAll("attempt to instantiate ths entry DTO from object with eclass BTSLemmaEntry should throw illegalargumentexception",
            () -> assertThrows(
                java.lang.IllegalArgumentException.class,
                () -> {mapper.readValue(
                    "{\"id\":\"1\",\"eclass\":\"BTSLemmaEntry\"}",
                    ThsEntryDto.class
                );}
            )
        );
    }
}