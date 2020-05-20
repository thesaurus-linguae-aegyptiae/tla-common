package tla.domain.dto;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Singular;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tla.domain.model.Language;
import tla.domain.model.LemmaWord;
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

    @Singular
    private SortedMap<Language, List<String>> translations;

    @Singular
    private List<LemmaWord> words;

    @Singular
    private List<AttestedTimespan> attestations;

    public LemmaDto() {
        this.translations = Collections.emptySortedMap();
        this.words = Collections.emptyList();
        this.attestations = Collections.emptyList();
    }

}
