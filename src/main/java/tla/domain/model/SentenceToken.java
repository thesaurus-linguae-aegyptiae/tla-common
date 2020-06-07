package tla.domain.model;

import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tla.domain.command.TypeSpec;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentenceToken {

    private String id;

    private String type;

    private String label;

    private Lemmatization lemma;

    private Flexion flexion;

    private String glyphs;

    private Transcription transcription;

    private SortedMap<Language, List<String>> translations;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flexion {

        private String verbal;
        private Long numeric;

    }

    @Getter
    @Setter
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
        private TypeSpec pos;

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
                        (l.getPos() == null || l.getPos().isEmpty())
                    );
                }
                return true;
            }
        }


    }
}
