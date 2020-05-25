package tla.domain.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tla.domain.dto.meta.NamedDocumentDto;
import tla.domain.model.meta.BTSeClass;

@Data
@NoArgsConstructor
@BTSeClass("BTSAnnotation")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AnnotationDto extends NamedDocumentDto {

    @JsonAlias("title")
    private String name;

    private Collection<String> body;

}