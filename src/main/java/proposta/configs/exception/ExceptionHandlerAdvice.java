package proposta.configs.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import proposta.configs.exception.customExceptions.ApiErrorException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorsDto> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errorsList = new ArrayList<>();

        List<FieldError> fieldErrorsList = exception.getBindingResult().getFieldErrors();

        fieldErrorsList.forEach(fieldError -> {
            String message = String.format("Campo: %s - %s", fieldError.getField(), messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
            errorsList.add(message);
        });

        StandardErrorsDto standardErrorsDto = new StandardErrorsDto(errorsList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardErrorsDto);
    }

    /**
     * Exception Handler para tratar a ApiErrorException
     * Essa exception é customizada para ser genérica
     * */
    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<StandardErrorsDto> handleApiErrorException(ApiErrorException exception) {
        List<String> messages = new ArrayList<>();
        messages.add(exception.getMessage());

        StandardErrorsDto standardErrorsDto = new StandardErrorsDto(messages);
        return ResponseEntity.status(exception.getHttpStatus()).body(standardErrorsDto);
    }

}
