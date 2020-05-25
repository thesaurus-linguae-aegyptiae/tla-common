package tla.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Representation of an identifier specifying an external resource describing
 * the same entity as the containing TLA object.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalReference implements Comparable<ExternalReference> {

    @NonNull
    private String id;

    private String type;

    /**
     * Order by <code>type</code>, then by <code>id</code>.
     */
    @Override
    public int compareTo(ExternalReference arg0) {
        if (this.getType() != null) {
            if (arg0.getType() != null) {
                int typeDist = this.getType().compareTo(arg0.getType());
                if (typeDist == 0) {
                    return this.getId().compareTo(arg0.getId());
                } else {
                    return typeDist;
                }
            } else {
                return 1;
            }
        } else {
            if (arg0.getType() != null) {
                return -1;
            } else {
                return this.getId().compareTo(arg0.getId());
            }
        }
    }

}