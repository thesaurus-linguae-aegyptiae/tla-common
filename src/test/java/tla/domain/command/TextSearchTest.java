package tla.domain.command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import tla.domain.dto.CommentDto;
import tla.domain.dto.TextDto;

public class TextSearchTest {

    @Test
    void test() {
        TextSearch cmd = new TextSearch();
        cmd.setDateId("THSID");
        assertAll("...",
            () -> assertNotNull(cmd.getDateId()),
            () -> assertNull(cmd.getSort()),
            () -> assertEquals(TextDto.class, cmd.getDTOClass())
        );
        assertDoesNotThrow(
            () -> cmd.setDTOClass(CommentDto.class)
        );
    }

}