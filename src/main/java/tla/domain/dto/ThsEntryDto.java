package tla.domain.dto;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.meta.NamedDocumentDto;
import tla.domain.model.Language;
import tla.domain.model.ObjectPath;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.UserFriendly;

/**
 * DTO Model for serial transfer of TLA thesaurus entry objects.
 */
@Data
@SuperBuilder
@BTSeClass("BTSThsEntry")
@EqualsAndHashCode(callSuper = true)
public class ThsEntryDto extends NamedDocumentDto implements UserFriendly {

    @JsonAlias("sortkey")
    private String sortKey;

    /**
     * object tree paths leading to this entry
     */
    private List<ObjectPath> paths;

    private String SUID;

    @Singular
    private SortedMap<Language, List<String>> translations;

    public ThsEntryDto() {
        this.translations = Collections.emptySortedMap();
    }

}