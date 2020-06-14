package tla.domain.model.meta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implementations provide a short, unique, memorable character sequence which
 * might be used as an alternative means of identification apart from
 * the potentially intimidatingly long object ID.
 */
public interface UserFriendly {
    /**
     * Get short, unique ID.
     */
    @JsonAlias({"hash", "sUID", "suid"})
    @JsonProperty("suid")
    public String getSUID();
}