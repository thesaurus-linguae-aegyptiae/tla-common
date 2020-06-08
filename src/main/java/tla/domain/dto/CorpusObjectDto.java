package tla.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.meta.NamedDocumentDto;
import tla.domain.model.Paths;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.UserFriendly;

@Data
@SuperBuilder
@NoArgsConstructor
@BTSeClass("BTSTCObject")
@EqualsAndHashCode(callSuper = true)
public class CorpusObjectDto extends NamedDocumentDto implements UserFriendly {

    private String sUID;

    private String corpus;
    /**
     * corpus object tree paths leading to this object
     */
    private Paths paths;

}