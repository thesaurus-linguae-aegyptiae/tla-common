package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.dto.CorpusObjectDto;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.TLADTO;

@Getter
@Setter
@BTSeClass("BTSTCObject")
@TLADTO(CorpusObjectDto.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CorpusObjectSearch extends SearchCommand<CorpusObjectDto> implements Expandable {

    /**
     * ID of the thesaurus entry representing the historic era
     * during which the searched for text was conceived.
     */
    private String dateId;

    private PassportSpec passport;

    private boolean expand;
    
    private String[] rootIds;
    
    private boolean mainNodes;
}