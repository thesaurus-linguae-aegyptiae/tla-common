package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TypeSpec {
    private String type;
    private String subtype;

    public static class EmptyObjectFilter {
        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof TypeSpec) {
                TypeSpec s = (TypeSpec) o;
                return (s.getType() == null || s.getType().isBlank()) &&
                    (s.getSubtype() == null || s.getSubtype().isBlank());
            }
            return true;
        }
        static EmptyObjectFilter inst = new EmptyObjectFilter();
    }

    /**
     * whether there's nothing worth serializing in here.
     */
    @JsonIgnore
    public boolean isEmpty() {
        return EmptyObjectFilter.inst.equals(this);
    }

}
