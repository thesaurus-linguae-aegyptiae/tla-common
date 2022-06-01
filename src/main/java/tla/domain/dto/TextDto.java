package tla.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.meta.NamedNodeDto;
import tla.domain.model.SentenceToken;
import tla.domain.model.SentenceToken.Glyphs;
import tla.domain.model.meta.BTSeClass;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@BTSeClass("BTSText")
@EqualsAndHashCode(callSuper = true)
public class TextDto extends NamedNodeDto {

    private String corpus;

    /**
     * Minimum and maximum number of words in this text's sentences.
     * These may differ due to ambivalences.
     */
    private WordCount wordCount;
    

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WordCount {
        private int min = 0;
        private int max = 0;
        /**
         * Purely for compatibility reasons!
         */
        public WordCount(int count) {
            this.min = count;
            this.max = count;
        }
    }

  
}