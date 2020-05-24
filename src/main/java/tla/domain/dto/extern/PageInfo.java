package tla.domain.dto.extern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageInfo {

    /**
     * number of current page
     */
    private int number;

    /**
     * number of results on one page
     */
    private int size;

    /**
     * number of results on this particular page
     */
    private int numberOfElements;

    /**
     * total number of elements
     */
    private long totalElements;

    /**
     * total number of pages
     */
    private int totalPages;

    public boolean isFirst() {
        return this.number == 0;
    }

    public boolean isLast() {
        return this.number == this.totalPages - 1;
    }

}