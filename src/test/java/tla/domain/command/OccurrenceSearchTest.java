package tla.domain.command;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import tla.domain.model.Language;
import tla.domain.model.SentenceToken.Lemmatization;
import tla.domain.model.Transcription;

public class OccurrenceSearchTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void donotSerializeEmptyElements() throws Exception {
        OccurrenceSearch cmd = new OccurrenceSearch();
        cmd.setLemma(new Lemmatization());
        cmd.getLemma().setPos(new TypeSpec());
        cmd.setTranscription(new Transcription());
        cmd.setTranslation(new TranslationSpec());
        cmd.getTranslation().setLang(new Language[]{});
        cmd.setType(new TypeSpec("", " "));
        String s = mapper.writeValueAsString(cmd);
        assertAll("should not serialize any content",
            () -> assertEquals(
                "{\"@class\":\"tla.domain.command.OccurrenceSearch\",\"@dto\":\"tla.domain.dto.SentenceDto\"}",
                s,
                "should be only @class"
            )
        );
    }

}