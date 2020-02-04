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

    @Test
    void comparison() throws Exception {
        ObjectReference ref = ObjectReference.builder().id("ID").eclass("BTSThsEntry").build();
        assertThrows(
            NullPointerException.class,
            () -> {ref.compareTo(null);}
        );
        ObjectReference ref2 = ObjectReference.builder().id("ID").eclass("BTSText").build();
        assertAll("test assumptions about string comparisons",
            () -> assertEquals(3, "h".compareTo("e"), "distance between 'e' and 'h' should be 3"),
            () -> assertEquals(1, "e".compareTo(""), "distance between 'e' and empty string should be 1"),
            () -> assertEquals(-1, "".compareTo("e"), "inverse should be -1"),
            () -> assertEquals("e".compareTo(""), "h".compareTo(""), "should not matter what letter is being compared to empty string")
        );
        assertAll("ref should be > than ref2",
            () -> assertEquals(3, ref.compareTo(ref2), "compareTo should return positive value"),
            () -> assertEquals(3, ref.getEclass().compareTo(ref2.getEclass()), "comparing eclass values should return positive"),
            () -> assertEquals(-3, ref2.compareTo(ref), "inversed comparison should return positive")
        );
    }
    
}