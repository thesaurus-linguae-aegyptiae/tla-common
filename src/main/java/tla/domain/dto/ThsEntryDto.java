package tla.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tla.domain.model.meta.BTSeClass;

/**
 * DTO Model for serial transfer of TLA thesaurus entry objects.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@BTSeClass("BTSThsEntry")
@EqualsAndHashCode(callSuper = true)
public class ThsEntryDto extends DocumentDto {

    @JsonAlias("sortkey")
    private String sortKey;

    public String getEclass() {
        return "BTSThsEntry";
    }

}