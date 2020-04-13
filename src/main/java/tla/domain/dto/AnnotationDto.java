package tla.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tla.domain.model.meta.BTSeClass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@BTSeClass("BTSAnnotation")
@EqualsAndHashCode(callSuper = true)
public class AnnotationDto extends DocumentDto {

    @JsonAlias("title")
    private String name;

    private String corpus;

}