package tla.domain.dto;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.meta.NamedNodeDto;
import tla.domain.model.Language;
import tla.domain.model.meta.BTSeClass;

/**
 * DTO Model for serial transfer of TLA thesaurus entry objects.
 */
@Data
@SuperBuilder
@BTSeClass("BTSThsEntry")
@EqualsAndHashCode(callSuper = true)
public class ThsEntryDto extends NamedNodeDto {

    @JsonAlias("sortkey")
    private String sortKey;

    public ThsEntryDto() {
    }

}