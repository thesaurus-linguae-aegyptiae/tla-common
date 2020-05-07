package tla.domain.command;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import tla.domain.model.Language;
import tla.domain.model.Script;

public class LemmaSearchTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserialize() throws Exception {
        LemmaSearch form = mapper.readValue(
            "{\"script\":[\"hieratic\"],\"translation\":{\"text\":\"trans\",\"lang\":[\"en\"]},\"transcription\":\"nfr\",\"bibliography\":\"wb\",\"pos\":{\"type\":\"noun\"}}",
            LemmaSearch.class
        );
        assertAll("does deserialized search form have all expected properties?",
            () -> assertNotNull(form.getTranslation(), "expect translation"),
            () -> assertEquals(1, form.getTranslation().getLang().length, "1 translation lang"),
            () -> assertEquals(Language.EN, form.getTranslation().getLang()[0], "english among translation languages?"),
            () -> assertEquals(1, form.getScript().length, "1 script"),
            () -> assertEquals(Script.HIERATIC, form.getScript()[0], "script is hieratic"),
            () -> assertEquals("wb", form.getBibliography(), "wb mentioned in bibliography passport field"),
            () -> assertNotNull(form.getPos(), "part of speech/wordclass is defined"),
            () -> assertEquals("noun", form.getPos().getType(), "correct POS")
        );
    }

}