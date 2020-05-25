package tla.domain.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tla.domain.command.TypeSpec;
import tla.domain.model.SentenceToken;

public class SentenceTest {

    @Test
    void builder() {
        SentenceDto s1 = SentenceDto.builder()
            .id("ID")
            .token(
                SentenceToken.builder()
                    .flexion(new SentenceToken.Flexion("default", 3L))
                    .lemma(new SentenceToken.Lemmatization(
                        "10060",
                        new TypeSpec("subst", "masc")
                    )).build()
            ).build();
        assertNotNull(s1.getTokens().get(0));
    }
}