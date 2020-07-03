package tla.domain.dto;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.meta.NamedNodeDto;
import tla.domain.model.meta.BTSeClass;

@Getter
@Setter
@SuperBuilder
@BTSeClass("BTSText")
@EqualsAndHashCode(callSuper = true)
public class TextDto extends NamedNodeDto {

    private String corpus;

    @JsonAlias("sentences")
    private List<String> sentenceIds;

    public TextDto() {
        this.sentenceIds = Collections.emptyList();
    }

}