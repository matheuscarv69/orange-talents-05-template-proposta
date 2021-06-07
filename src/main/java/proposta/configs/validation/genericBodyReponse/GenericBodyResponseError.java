package proposta.configs.validation.genericBodyReponse;

import org.springframework.http.HttpStatus;

public class GenericBodyResponseError {

    private HttpStatus status;
    private String message;

    public GenericBodyResponseError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
