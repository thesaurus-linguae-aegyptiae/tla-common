package tla.domain.model;

import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tla.domain.command.TypeSpec;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentenceToken {

    private String id;

    private String type = "word";

    private String label;

     @JsonInclude(
         value = JsonInclude.Include.CUSTOM,
         valueFilter = Lemmatization.EmptyObjectFilter.class
     )
    private Lemmatization lemma;

    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = Flexion.EmptyObjectFilter.class
    )
    private Flexion flexion;

    private String glyphs;

    private Transcription transcription;

    private SortedMap<Language, List<String>> translations;

    /**
     * types of standoff objects (annotations, comments, subtexts) referencing
     * a text range including this token.
     */
    private List<String> annoTypes;

    public SentenceToken() {
        this.lemma = new Lemmatization();
        this.flexion = new Flexion();
    }

    public SentenceToken(Transcription transcription, String glyphs) {
        this();
        this.transcription = transcription;
        this.glyphs = glyphs;
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flexion {

        /**
         * BTS Flexcode
         */
        private Long numeric;
        /**
         * BTS glossing
         */
        @JsonAlias({"verbal", "bgloss", "bGloss"})
        private String btsGloss;
        /**
         * Leipzig Glossing Rules glossing
         */
        @JsonAlias({"glossing", "lgloss", "lGloss"})
        private String lingGloss;

        public static class EmptyObjectFilter {
            public boolean equals(Object obj) {
                if (obj != null && obj instanceof Flexion) {
                    Flexion f = (Flexion) obj;
                    return (f.btsGloss == null || f.btsGloss.isBlank()) &&
                        (f.numeric == null || f.numeric == 0) &&
                        (f.lingGloss == null || f.lingGloss.isBlank());
                }
                return true;
            }
        }

    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Lemmatization {

        /**
         * lemma ID
         */
        private String id;

        /**
         * lemma entry part of speech information
         */
        @JsonProperty("POS")
        @JsonAlias({"pos", "POS"})
        private TypeSpec partOfSpeech;

        /**
         * for jackson to determine "empty" instances
         */
        public static class EmptyObjectFilter {
            @Override
            public boolean equals(Object obj) {
                if (obj != null && obj instanceof Lemmatization) {
                    Lemmatization l = (Lemmatization) obj;
                    return (
                        (l.getId() == null || l.getId().isBlank()) &&
                        (l.getPartOfSpeech() == null || l.getPartOfSpeech().isEmpty())
                    );
                }
                return true;
            }
        }
    }

}
