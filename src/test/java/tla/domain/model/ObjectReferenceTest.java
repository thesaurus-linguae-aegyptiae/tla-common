package tla.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectReferenceTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserialize_partial_id_eclass() throws Exception {
        ObjectReference ref = mapper.readValue(
            "{\"id\": \"ID\", \"eclass\": \"eclass\"}",
            ObjectReference.class
        );
        assertAll("should deserialize ths ref with id value set",
            () -> assertTrue(ref != null, "result should not be null"),
            () -> assertEquals("ID", ref.getId(), "id should be set"),
            () -> assertEquals("eclass", ref.getEclass(), "eclass should be set")
        );
    }
    
}