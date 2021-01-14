package tla.domain.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassportSpec extends HashMap<String, PassportSpec.PassportSpecValue> {

    private static final long serialVersionUID = 1995818429616022627L;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static interface PassportSpecValue {
        public Set<String> getValues();
        public PassportSpecValue setValues(Collection<String> values);
        public PassportSpecValue merge(PassportSpecValue spec);
        @JsonCreator
        public static PassportSpecValue of(
            @JsonProperty(value = "values", required = true) Collection<String> values,
            @JsonProperty(value = "expand", required = false) Boolean expand
        ) {
            if (expand != null) {
                return ThsRefPassportValue.of(values, expand);
            } else {
                return LiteralPassportValue.of(values);
            }
        }
        @JsonIgnore
        public default boolean isEmpty() {
            return this.getValues().isEmpty();
        }
    }

    @Getter
    public static class LiteralPassportValue implements PassportSpecValue {
        private Set<String> values;
        public LiteralPassportValue() {
            this.values = new HashSet<>();
        }
        public static PassportSpecValue of(Collection<String> values) {
            return new LiteralPassportValue().setValues(values);
        }
        public PassportSpecValue setValues(Collection<String> values) {
            this.values.addAll(values);
            return this;
        }
        public PassportSpecValue merge(PassportSpecValue spec) {
            return this.setValues(spec.getValues());
        }
    }

    @Getter
    public static class ThsRefPassportValue implements PassportSpecValue {
        private Set<String> values;
        @Setter
        private boolean expand;
        public ThsRefPassportValue() {
            this.values = new HashSet<>();
        }
        public static ThsRefPassportValue of(Collection<String> values, boolean expand) {
            ThsRefPassportValue val = (ThsRefPassportValue) new ThsRefPassportValue().setValues(values);
            val.setExpand(expand);
            return val;
        }
        public PassportSpecValue setValues(Collection<String> values) {
            this.values.addAll(values);
            return this;
        }
        public PassportSpecValue merge(PassportSpecValue spec) {
            this.setValues(spec.getValues());
            if (spec instanceof ThsRefPassportValue) {
                this.setExpand(((ThsRefPassportValue) spec).isExpand());
            }
            return this;
        }
    }

    public PassportSpec addAll(PassportSpec spec) {
        spec.entrySet().forEach(
            e -> this.merge(
                e.getKey(),
                e.getValue(),
                (prev, cur) -> prev.merge(cur)
            )
        );
        return this;
    }

    public PassportSpecValue put(String key, PassportSpecValue value) {
        return super.merge(
            key, value, (prev, cur) -> prev.merge(cur)
        );
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() || this.values().stream().allMatch(
            PassportSpecValue::isEmpty
        );
    }

}