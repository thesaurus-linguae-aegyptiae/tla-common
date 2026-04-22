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

	private Integer distance;

	public String getLemmaId1() {
		return lemmaId1;
	}
	
	public String getLemmaId2() {
		return lemmaId2;
	}
	
	public Integer getDistance() {
		return distance;
	}
}