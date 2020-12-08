package tla.domain.command;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import tla.domain.model.Language;
import tla.domain.model.SentenceToken.Lemmatization;
import tla.domain.model.Transcription;

public class SentenceSearchTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void donotSerializeEmptyElements() throws Exception {
        SentenceSearch cmd = new SentenceSearch();
        cmd.setLemma(new Lemmatization());
        cmd.getLemma().setPartOfSpeech(new TypeSpec());
        cmd.setTranscription(new Transcription());
        cmd.setTranslation(new TranslationSpec());
        cmd.getTranslation().setLang(new Language[]{});
        cmd.setType(new TypeSpec("", " "));
        cmd.setPassport(new PassportSpec());
        String s = mapper.writeValueAsString(cmd);
        assertAll("should not serialize any content",
            () -> assertEquals(
                String.format("{\"@class\":\"%s\",\"@dto\":\"tla.domain.dto.SentenceDto\"}", cmd.getClass().getName()),
                s,
                "should be only @class"
            )
        );
    }

}