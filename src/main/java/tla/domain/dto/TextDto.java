package tla.domain.dto;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.meta.NamedDocumentDto;
import tla.domain.model.Paths;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.UserFriendly;

@Data
@SuperBuilder
@BTSeClass("BTSText")
@EqualsAndHashCode(callSuper = true)
public class TextDto extends NamedDocumentDto implements UserFriendly {

    private String corpus;

    /**
     * object tree paths leading to this text
     */
    private Paths paths;

    private String sUID;

    @JsonAlias("sentences")
    private List<String> sentenceIds;

    public TextDto() {
        this.sentenceIds = Collections.emptyList();
    }

}