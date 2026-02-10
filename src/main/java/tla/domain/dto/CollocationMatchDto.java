package tla.domain.dto;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.meta.AbstractDto;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.Resolvable;

@Data
@NoArgsConstructor
@BTSeClass("BTSCollocation")
@SuperBuilder
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class CollocationMatchDto extends AbstractDto {
	private String lemmaId1; 	//todo remove
	private String lemmaId2;	//todo remove
	private String tokenId1;
	private String tokenId2;
	private Integer position1;		//todo remove; pürfen ggf. negative distanz
	private Integer position2;		//todo remove
	private Integer distance;
	
	private TextDto text;
	
	private SentenceDto sentence_1;
	
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private SentenceDto sentence_2;

	@Override
	public Map<String, SortedSet<Resolvable>> getRelations() {
		// TODO Auto-generated method stub
		return null;
	}

	public CollocationMatchDto(String lemmaId1, String lemmaId2, String tokenId1, String tokenId2, Integer position1,
			Integer position2, Integer distance, TextDto text, SentenceDto sentence_1, SentenceDto sentence_2) {
		try {
			this.setEclass("BTSCollocation");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// super();
		this.lemmaId1 = lemmaId1;
		this.lemmaId2 = lemmaId2;
		this.tokenId1 = tokenId1;
		this.tokenId2 = tokenId2;
		this.position1 = position1;
		this.position2 = position2;
		this.distance = distance;
		this.text = text;
		this.sentence_1 = sentence_1;
		this.sentence_2 = sentence_2;
	}
}