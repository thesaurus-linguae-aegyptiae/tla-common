package tla.domain.dto.extern;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tla.domain.command.SearchCommand;
import tla.domain.dto.meta.AbstractDto;
import tla.domain.dto.meta.DocumentDto;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultsWrapper<T extends AbstractDto> {

    /**
     * the actual search results, one page full of them.
     */
    @JsonAlias("content")
    private List<T> results;

    /**
     * any objects directly related to the search results in a way
     * that we might need those to render the result.
     */
    @Setter(AccessLevel.NONE)
    private Map<String, Map<String, DocumentDto>> related;

    /**
     * the original command DTO to which this is the response.
     */
    private SearchCommand<? extends AbstractDto> query;

    /**
     * page information
     */
    private PageInfo page;

    /**
     * ES aggregation buckets little reorganized and given a different
     * name.
     */
    private Map<String, Map<String, Long>> facets;

    public SearchResultsWrapper(List<T> hits, SearchCommand<? extends AbstractDto> query) {
        this.results = hits;
        this.query = query;
    }

    public SearchResultsWrapper(
        List<T> hits, SearchCommand<? extends AbstractDto> query, PageInfo page
    ) throws Exception {
        this(hits, query);
        this.page = page;
        if (page.getNumberOfElements() != this.results.size()) {
            throw new IllegalArgumentException(
                String.format(
                    "page info element count %s does not match actual element count %s",
                    page.getNumberOfElements(),
                    this.results.size()
                )
            );
        }
        if (page.getTotalElements() < page.getNumberOfElements()) {
            throw new IllegalArgumentException(
                String.format(
                    "total element count %s can't be less than number of elements on page: %s",
                    page.getTotalElements(),
                    page.getNumberOfElements()
                )
            );
        }
        if (page.getSize() < 1) {
            throw new IllegalArgumentException("page size can't be 0");
        }
        if (page.getTotalElements() > 0 && page.getTotalPages() != Math.floorDiv(page.getTotalElements(), page.getSize()) + 1) {
            throw new IllegalArgumentException(
                String.format(
                    "total page count can't be %s when page size is %s and total element count is %s",
                    page.getTotalPages(),
                    page.getSize(),
                    page.getTotalElements()
                )
            );
        }
    }

    public SearchResultsWrapper(
        List<T> items, SearchCommand<? extends AbstractDto> query,
        PageInfo page, Map<String, Map<String, Long>> facets
    ) throws Exception {
        this(items, query, page);
        this.facets = facets;
    }

}