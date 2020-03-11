package tla.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import tla.domain.model.Paths;
import tla.domain.model.meta.BTSeClass;

@Data
@SuperBuilder
@AllArgsConstructor
@BTSeClass("BTSTCObject")
@EqualsAndHashCode(callSuper = true)
public class CorpusObjectDto extends DocumentDto {

    private String corpus;
    private Paths paths;

}