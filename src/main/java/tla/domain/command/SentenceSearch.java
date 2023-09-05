package tla.domain.command;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import tla.domain.dto.SentenceDto;
import tla.domain.dto.SentenceDto.SentenceContext;
import tla.domain.model.Transcription;
import tla.domain.model.SentenceToken.Lemmatization;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.TLADTO;

@Setter
@Getter
@TLADTO(SentenceDto.class)
@BTSeClass("BTSSentence")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentenceSearch extends MultiLingSearchCommand<SentenceDto> {
	
	 private SentenceContext context;


    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class TokenSpec {
    	
    	public String id;
        /**
         * looking for usages of a specific lemma entry.
         */
        @JsonInclude(
            value = JsonInclude.Include.CUSTOM,
            valueFilter = Lemmatization.EmptyObjectFilter.class
        )
        private Lemmatization lemma;
        /**
         * word uses these hieroglyphs
         */
        private String glyphs;
        /**
         * looking for word uses written in a specific way.
         */
        @JsonInclude(
            value = JsonInclude.Include.CUSTOM,
            valueFilter = Transcription.EmptyObjectFilter.class
        )
        private Transcription transcription;
        /**
         * token translation
         */
        @JsonInclude(
            value = JsonInclude.Include.CUSTOM,
            valueFilter = TranslationSpec.EmptyObjectFilter.class
        )
        private TranslationSpec translation;

        @JsonIgnore
        public boolean isEmpty() {
            return (
                (this.lemma == null || this.lemma.isEmpty()) &&
                (this.glyphs == null || this.glyphs.isBlank()) &&
                (this.transcription == null || this.transcription.isEmpty()) &&
                (this.translation == null || this.translation.isEmpty())
            );
        }

        public static class EmptyObjectFilter {
            @Override
            public boolean equals(Object o) {
                if (o != null && o instanceof Collection) {
                    return ((Collection<?>) o).stream().allMatch(
                        item -> item == null || (item instanceof TokenSpec && ((TokenSpec) item).isEmpty())
                    );
                }
                return true;
            }
        }
    }

    /**
     * type of the surrounding sentence
     */
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TypeSpec.EmptyObjectFilter.class
    )
    private TypeSpec type;
    


    private PassportSpec passport;

    @Singular
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TokenSpec.EmptyObjectFilter.class
    )
    private List<TokenSpec> tokens;

}