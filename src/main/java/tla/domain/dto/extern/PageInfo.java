package tla.domain.dto.extern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageInfo {

    private int number;

    private int size;

    private int numberOfElements;

    private long totalElements;

    private int totalPages;

    public boolean isFirst() {
        return this.number == 0;
    }

    public boolean isLast() {
        return this.number == this.totalPages - 1;
    }

}