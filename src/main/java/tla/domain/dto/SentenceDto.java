package tla.domain.dto;

import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import tla.domain.dto.meta.AbstractDto;
import tla.domain.model.Language;
import tla.domain.model.ObjectReference;
import tla.domain.model.SentenceToken;
import tla.domain.model.Transcription;
import tla.domain.model.Glyphs;
import tla.domain.model.meta.BTSeClass;

@Getter
@Setter
@NoArgsConstructor
@BTSeClass("BTSSentence")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentenceDto extends DocumentDto {

    /**
     * How sentence relates to a text's contents.
     */
    private SentenceContext context;

    private String type;

    @Singular
    private SortedMap<Language, List<String>> translations;
    
    @JsonInclude(
            value = JsonInclude.Include.CUSTOM,
            valueFilter = Glyphs.EmptyObjectFilter.class
        )
        
    private Glyphs glyphs;

    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = Transcription.EmptyObjectFilter.class
    )
    
    private Transcription transcription;

    @Singular
    private List<SentenceToken> tokens;

    private Integer wordCount;
    
    public ObjectReference toObjectReference() {
        return ObjectReference.from(this);
    }

    /**
     * Information about where in which text a sentence is to be located.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class SentenceContext {
        /**
         * ID of the text containing sentence.
         */
        private String textId;
        /**
         * Type of text containing sentence. Might be null.
         */
        private String textType;
        /**
         * How many sentences come before this sentence within the
         * text's contents.
         */
        @JsonAlias({"pos"})
        @JsonProperty("pos")
        private int position;
        /**
         * Value of the last new line indicator found before beginning of this sentence.
         * Might be null.
         */
        private String line;
        /**
         * Value of last paragraph indicator found before beginning of sentence.
         */
        private String paragraph;
        /**
         * Number of variants which exist for the same sentence due to ambivalences.
         */
        private int variants;
    }

}
