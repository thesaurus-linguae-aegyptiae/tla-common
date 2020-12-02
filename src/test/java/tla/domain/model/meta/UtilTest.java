package tla.domain.model.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tla.domain.dto.LemmaDto;

public class UtilTest {

    @TLADTO(LemmaDto.class)
    private static class ModelClass {}

    @Test
    void testEclassExtraction() {
        assertEquals("BTSLemmaEntry", Util.extractEclass(ModelClass.class), "eclass extracted from TLADTO annotation");
    }
}