package tla.domain.dto;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import tla.domain.command.TypeSpec;
import tla.domain.dto.meta.NamedDocumentDto;
import tla.domain.model.Language;
import tla.domain.model.SentenceToken;
import tla.domain.model.SentenceToken.Glyphs;
import tla.domain.model.SentenceToken.Lemmatization;
import tla.domain.model.extern.AttestedTimespan;
import tla.domain.model.meta.BTSeClass;

/**
 * DTO Model for serial transfer of TLA lemma entry objects.
 */
@Data
@SuperBuilder
@BTSeClass("BTSLemmaEntry")
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LemmaDto extends NamedDocumentDto {

    @JsonAlias({"sortString", "sort_string", "sort_key"})
    private String sortKey;
   
    //private String mdc;
    @JsonInclude(
            value = JsonInclude.Include.CUSTOM,
            valueFilter = Glyphs.EmptyObjectFilter.class
        )
    private Glyphs glyphs;

    @Singular
    private SortedMap<Language, List<String>> translations;
    
    private Transcription transcription;

    @Singular
    private List<SentenceToken> words;
    
    @Singular
    private int attestedSentencesCount;
    
    
    public static class Period{
    	 /** first year */
        @Setter
        @Getter
        private int begin;
        /** last year */
        @Setter
        @Getter
        private int end;
    }
    
    private Period timeSpan;

    public LemmaDto() {
    	this.glyphs=new Glyphs();
    	this.transcription=new Transcription();
        this.translations = Collections.emptySortedMap();
        this.words = Collections.emptyList();
          }
    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Glyphs {
    	 @JsonAlias({"mdc_compact"})
    	private String mdcCompact;
    	 @JsonAlias({"unicode"})
        private String unicode;
    

        @JsonIgnore
      
        public boolean isEmpty() {
            return (
                (this.mdcCompact == null || this.mdcCompact.isBlank()) &&
                (this.unicode == null || this.unicode.isBlank())
            );
        }

        public static class EmptyObjectFilter {
            @Override
            public boolean equals(Object obj) {
                if (obj != null && obj instanceof Glyphs) {
                    return ((Glyphs) obj).isEmpty();
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
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Transcription {
    	 @JsonAlias({"mdc"})
    	private String mdc;
    	 @JsonAlias({"unicode"})
        private String unicode;
    

        @JsonIgnore
      
        public boolean isEmpty() {
            return (
                (this.mdc == null || this.mdc.isBlank()) &&
                (this.unicode == null || this.unicode.isBlank())
            );
        }

        public static class EmptyObjectFilter {
            @Override
            public boolean equals(Object obj) {
                if (obj != null && obj instanceof Glyphs) {
                    return ((Transcription) obj).isEmpty();
                }
                return true;
            }
        }
    }
}
