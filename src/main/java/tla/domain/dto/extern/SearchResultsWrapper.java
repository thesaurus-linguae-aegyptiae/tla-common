package tla.domain.dto.extern;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tla.domain.command.LemmaSearch;
import tla.domain.dto.meta.AbstractDto;

@Getter
@NoArgsConstructor
public class SearchResultsWrapper<T extends AbstractDto> {

    private List<T> content;

    private LemmaSearch query;

    private PageInfo page;

    public SearchResultsWrapper(List<T> content, LemmaSearch query, PageInfo page) throws Exception {
        this.content = content;
        this.query = query;
        this.page = page;
        if (page.getNumberOfElements() != content.size()) {
            throw new IllegalArgumentException(
                String.format(
                    "page info element count %s does not match actual element count %s",
                    page.getNumberOfElements(),
                    content.size()
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

}