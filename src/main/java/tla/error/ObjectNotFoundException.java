package tla.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * Supposed to be thrown whenever retrieval of an identified resource fails.
 */
@Getter
@Setter
@Status(404)
@JsonPropertyOrder({"className", "message", "objectId", "eclass"})
public class ObjectNotFoundException extends TlaStatusCodeException {

    private static final long serialVersionUID = 3545121302433575193L;

    final static String MSG_TEMPLATE = "The %sobject with ID '%s' could not be found!";

    private String objectId;
    private String eclass;

    public ObjectNotFoundException(String id) {
        this(id, null);
    }

    public ObjectNotFoundException(String id, String eclass) {
        this.objectId = id;
        this.eclass = eclass;
    }

    @Override
    public String getMessage() {
        return String.format(
            MSG_TEMPLATE,
            (this.eclass != null) ? (this.eclass + " ") : "",
            this.objectId
        );
    }

}
