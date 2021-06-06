package service;

public class UserNotExistsException extends ServiceException {
    private final Integer id;

    public UserNotExistsException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
