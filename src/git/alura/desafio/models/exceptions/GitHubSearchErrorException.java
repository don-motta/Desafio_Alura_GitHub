package git.alura.desafio.models.exceptions;

public class GitHubSearchErrorException extends RuntimeException {
    public GitHubSearchErrorException(String message) {
        super(message);
    }
}
