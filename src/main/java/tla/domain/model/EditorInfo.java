package tla.domain.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * Holds information about a document's authoring and contributing editors, and the time of the
 * latest change.
 */
@Data
public class EditorInfo {

    private String author;
    private String type;
    private List<String> contributors;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updated;

}
