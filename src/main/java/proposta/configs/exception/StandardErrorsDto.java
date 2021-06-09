package proposta.configs.exception;

import java.util.List;

public class StandardErrorsDto {

    private List<String> errors;

    public StandardErrorsDto(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
