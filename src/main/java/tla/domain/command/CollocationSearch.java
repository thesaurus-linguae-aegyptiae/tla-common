package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.TLADTO;
import tla.domain.dto.CollocationMatchDto;

@Getter
@Setter
@BTSeClass("BTSCollocation")
@TLADTO(CollocationMatchDto.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollocationSearch extends SearchCommand<CollocationMatchDto> {

	private String sort;

	private String lemmaId1;

	private String lemmaId2;
	
	private Boolean lemmaId1Extended;
	
	private Boolean lemmaId2Extended;

	private Integer distance;
}