package tla.domain.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.meta.NamedDocumentDto;
import tla.domain.model.ObjectPath;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.UserFriendly;

@Data
@SuperBuilder
@NoArgsConstructor
@BTSeClass("BTSTCObject")
@EqualsAndHashCode(callSuper = true)
public class CorpusObjectDto extends NamedDocumentDto implements UserFriendly {

    private String SUID;

    private String corpus;
    /**
     * corpus object tree paths leading to this object
     */
    private List<ObjectPath> paths;

}