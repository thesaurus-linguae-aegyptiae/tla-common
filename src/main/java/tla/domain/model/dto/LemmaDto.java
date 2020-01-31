package tla.domain.model.dto;

import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import tla.domain.model.ExternalReference;
import tla.domain.model.Language;

/**
 * DTO Model for serial transfer of TLA lemma entry objects.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class LemmaDto extends DocumentDto {

    @JsonAlias("sort_string")
    private String sortString;

    @Singular
    private SortedMap<Language, List<String>> translations;

    @Singular
    private SortedMap<String, List<ExternalReference>> externalReferences;
}
