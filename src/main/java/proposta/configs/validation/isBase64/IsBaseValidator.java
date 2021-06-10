package proposta.configs.validation.isBase64;

import org.springframework.http.HttpStatus;
import proposta.configs.exception.customExceptions.ApiErrorException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsBaseValidator implements ConstraintValidator<IsBase64, String> {

    @Override
    public void initialize(IsBase64 constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String encodedString, ConstraintValidatorContext constraintValidatorContext) {

        try {
            byte[] decode = java.util.Base64.getDecoder().decode(encodedString);
            String encode = java.util.Base64.getEncoder().encodeToString(decode);

            boolean isBase64 = encodedString.equals(encode);
            return isBase64;
        } catch (IllegalArgumentException e) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Fingerprint inválido");
        }
    }


}
