package tla.domain.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Holds information about a document's authoring and contributing editors, and the time of the
 * latest change.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EditorInfo {

    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    static {
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @EqualsAndHashCode.Include
    private String author;

    @EqualsAndHashCode.Include
    private String type;

    @EqualsAndHashCode.Include
    private List<String> contributors;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date updated;

    @EqualsAndHashCode.Include
    @JsonIgnore
    public String getDateOfLatestUpdate() {
        return dateFormatter.format(this.updated);
    }

}
