package service;

public class UserPasswordIncorrectException extends ServiceException {
    private final Integer id;

    public UserPasswordIncorrectException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
