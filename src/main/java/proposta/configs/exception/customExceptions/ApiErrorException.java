package proposta.configs.exception.customExceptions;

import org.springframework.http.HttpStatus;

/**
 *  Exception customizada para lançar erros
 *  genéricos na aplicação passando o httpstatus
 *  e a mensagem desejada
 * */
public class ApiErrorException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public ApiErrorException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
