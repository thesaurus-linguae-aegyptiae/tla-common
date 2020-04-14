package tla.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tla.domain.model.Paths;
import tla.domain.model.meta.BTSeClass;

@Data
@SuperBuilder
@NoArgsConstructor
@BTSeClass("BTSTCObject")
@EqualsAndHashCode(callSuper = true)
public class CorpusObjectDto extends DocumentDto {

    private String corpus;
    private Paths paths;

}