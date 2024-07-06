package git.alura.desafio.models.exceptions;

public class InvalidPasswordException extends RuntimeException {
    private String message;

    public InvalidPasswordException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
