package tla.domain.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tla.domain.dto.TextDto;

public class IOTest {

    @Test
    void deserializeDoc() throws Exception {
        TextDto text = IO.loadFromFile(
            "src/test/resources/sample/text/A2HWO5CX6RCQ5IBSV2MHAAIYA4.json",
            TextDto.class
        );
        assertAll("deserialization from file",
            () -> assertNotNull(text, "object should not be null"),
            () -> assertNotNull(text.getPassport(), "contained objects should not be null")
        );
    }
}
