package tla.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnnotationDto extends DocumentDto {

    private String corpus;

    public String getEclass() {
        return "BTSAnnotation";
    }

}