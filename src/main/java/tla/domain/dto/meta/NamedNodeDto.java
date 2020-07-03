package tla.domain.dto.meta;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tla.domain.model.ObjectPath;
import tla.domain.model.meta.Hierarchic;
import tla.domain.model.meta.UserFriendly;

/**
 * Base class for document types whose instances interrelate within an explicit hierarchy.
 *
 * <p>Each document of a type inheriting from this one does have 2 special methods:
 *
 * <ol><li>{@link #getPaths()}: One or more paths connecting it to one or more <em>root
 * documents</em> within the type's document hierarchy.</li>
 * <li>{@link #getSUID()}: A short and unique ID which can be used to address the document
 * without having to memorize its potentially long and indistinguishable standard ID.
 * </li></ol>
 * </p>
 *
 * @author Jakob Hoeper
 * @see ObjectPath
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class NamedNodeDto extends NamedDocumentDto implements Hierarchic, UserFriendly {

    /**
     * Object tree paths leading to this document.
     */
    private List<ObjectPath> paths;

    /**
     * Short, unique ID.
     */
    private String SUID;

}