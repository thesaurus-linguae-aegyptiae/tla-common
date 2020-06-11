package tla.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonAlias({"hash", "suid"})
    @JsonProperty("suid")
    private String SUID;

    private String corpus;
    /**
     * corpus object tree paths leading to this object
     */
    private List<ObjectPath> paths;

}