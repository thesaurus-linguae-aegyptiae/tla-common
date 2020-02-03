package tla.domain.model.dto;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import tla.domain.model.Language;

/**
 * DTO Model for serial transfer of TLA lemma entry objects.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LemmaDto extends DocumentDto {

    @JsonAlias("sort_string")
    private String sortString;

    @Singular
    private SortedMap<Language, List<String>> translations;

    public LemmaDto() {
        translations = Collections.emptySortedMap();
    }

}
