package tla.domain.dto.meta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tla.domain.model.EditorInfo;

/**
 * TLA DTO model semi-base class
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class DocumentDto extends AbstractDto {

    @JsonAlias("revisionState")
    private String reviewState;

    private EditorInfo editors;

}
