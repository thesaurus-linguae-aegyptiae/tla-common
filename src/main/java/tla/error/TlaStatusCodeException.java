package tla.error;

import java.lang.annotation.Annotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"stackTrace", "ourStackTrace", "localizedMessage"})
@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
public abstract class TlaStatusCodeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Return (HTTP) status code. Can be <code>null</code>.
     */
    public Integer getCode() {
        for (Annotation a : this.getClass().getAnnotations()) {
            if (a instanceof Status) {
                return ((Status) a).value();
            }
        }
        return null;
    }

    /**
     * doesn't really do anything
     */
    public void setCode(Integer code) {
        if (!code.equals(this.getCode())) {
            log.warn(
                "Unexpected status code {} during serialization of {} (expected {})",
                code, this.getClass().getName(), this.getCode()
            );
        }
    }

}