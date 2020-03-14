package tla.domain.dto;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import tla.domain.model.Paths;
import tla.domain.model.meta.BTSeClass;

@Data
@SuperBuilder
@AllArgsConstructor
@BTSeClass("BTSText")
@EqualsAndHashCode(callSuper = true)
public class TextDto extends DocumentDto {

    private String corpus;
    private Paths paths;

    @JsonAlias("sentences")
    private List<String> sentenceIds;

    public TextDto() {
        this.sentenceIds = Collections.emptyList();
    }

}