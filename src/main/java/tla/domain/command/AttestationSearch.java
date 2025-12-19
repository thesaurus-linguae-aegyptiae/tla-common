package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.dto.SentenceDto;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.TLADTO;

@Setter
@Getter
@TLADTO(SentenceDto.class)
@BTSeClass("BTSSentence")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttestationSearch extends SearchCommand<SentenceDto> {
	
	private String lemma_id;

	public String getLemmaId() {
		return lemma_id;
	}

	public boolean isSuccessor() {
		return successor;
	}

	public boolean isPredecessor() {
		return predecessor;
	}

	public boolean isComposes() {
		return composes;
	}

	public boolean isContains() {
		return contains;
	}

	public boolean isReferencing() {
		return referencing;
	}
	
	public boolean isReferencedBy() {
		return referencedBy;
	}
	
	public boolean isRootOf() {
		return rootOf;
	}

	public void setLemmaId(String lemma_id) {
		this.lemma_id = lemma_id;
	}

	public void setSuccessor(boolean successor) {
		this.successor = successor;
	}

	public void setPredecessor(boolean predecessor) {
		this.predecessor = predecessor;
	}

	public void setComposes(boolean composes) {
		this.composes = composes;
	}

	public void setContains(boolean contains) {
		this.contains = contains;
	}

	public void setReferencing(boolean referencing) {
		this.referencing = referencing;
	}
	
	public void setReferenedBy(boolean referencedBy) {
		this.referencedBy = referencedBy;
	}
	
	public void setRootOf(boolean rootOf) {
		this.rootOf = rootOf;
	}

	private boolean successor = false;
	
	private boolean predecessor = false;
	
	private boolean composes = false;
	
	private boolean contains = false;
	
	private boolean referencing = false;
	
	private boolean referencedBy = false;
	
	private boolean rootOf = false;
}
