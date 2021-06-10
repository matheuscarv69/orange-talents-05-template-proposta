package proposta.configs.validation.isBase64;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsBaseValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsBase64 {

    String message() default "Biometria inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String encodedString() default "";

}
