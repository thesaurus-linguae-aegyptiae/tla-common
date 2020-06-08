package tla.domain.model.meta;

/**
 * Implementations provide a short, unique, memorable character sequence which
 * might be used as an alternative means of identification apart from
 * the potentially intimidatingly long object ID.
 */
public interface UserFriendly {
    /**
     * Get short, unique ID.
     */
    public String getSUID();
}