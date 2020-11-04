package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.dto.TextDto;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.TLADTO;

@Getter
@Setter
@TLADTO(TextDto.class)
@BTSeClass("BTSText")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TextSearch extends SearchCommand<TextDto> {

    /**
     * ID of the thesaurus entry representing the historic era
     * during which the searched for text was conceived.
     */
    private String dateId;

}