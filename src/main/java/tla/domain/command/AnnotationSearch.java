package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

import tla.domain.dto.AnnotationDto;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.TLADTO;

@Getter
@Setter
@BTSeClass("BTSAnnotation")
@TLADTO(AnnotationDto.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AnnotationSearch {

	@JsonInclude(
		value = JsonInclude.Include.CUSTOM,
		valueFilter = TypeSpec.EmptyObjectFilter.class
	)
	private TypeSpec type;

	private String body;

}
