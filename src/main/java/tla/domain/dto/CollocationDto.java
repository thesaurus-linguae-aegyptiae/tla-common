package tla.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tla.domain.dto.meta.AbstractDto;
import tla.domain.model.ObjectPath;
import tla.domain.model.meta.BTSeClass;

@Getter
@Setter
@NoArgsConstructor
@BTSeClass("BTSCollocation") // TODO
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollocationDto extends AbstractDto {

	List<SentenceDto> sentences = new ArrayList<SentenceDto>();
	List<String> tokenIds = new ArrayList<String>();
	int distance;
	
	private String textId;
	
	private List<ObjectPath> paths;

	public CollocationDto(List<SentenceDto> sentences, List<String> tokenIds, int distance, String textId, List<ObjectPath> paths) {
		this.sentences = sentences;
		this.tokenIds = tokenIds;
		this.distance = distance;
		this.textId = textId;
		this.paths = paths;
	}
}
