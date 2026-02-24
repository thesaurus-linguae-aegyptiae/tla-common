package tla.domain.model;

import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import tla.domain.dto.LemmaDto.TimeSpan;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ObjectReferenceLemmaExtended extends ObjectReference {
	
	private String subtype;
	private String reviewState;
	private Glyphs glyphs;
	@Singular
	private SortedMap<Language, List<String>> translations;
	private Transcription transcription;
	private int attestedSentencesCount;
	private TimeSpan timeSpan;
	private List<String> bibliography;
	
}