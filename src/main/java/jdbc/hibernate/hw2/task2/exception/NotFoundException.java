package jdbc.hibernate.hw2.task2.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public static <T> NotFoundException ofId(Class<?> type, T id) {
        return new NotFoundException(type.getName() + " with ID = " + id + " not found");
    }

    public static NotFoundException ofName(Class<?> type, String name) {
        return new NotFoundException(type.getName() + " with name = " + name + " not found");
    }
}
