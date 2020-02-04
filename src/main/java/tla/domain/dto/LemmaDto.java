package tla.domain.dto;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tla.domain.model.Language;
import tla.domain.model.LemmaWord;

/**
 * DTO Model for serial transfer of TLA lemma entry objects.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder(alphabetic = true)
public class LemmaDto extends DocumentDto {

    @JsonAlias("sort_string")
    private String sortString;

    @Singular
    private SortedMap<Language, List<String>> translations;

    @Singular
    private List<LemmaWord> words;

    public LemmaDto() {
        this.translations = Collections.emptySortedMap();
        this.words = Collections.emptyList();
    }

    public String getEclass() {
        return "BTSLemmaEntry";
    }

}
