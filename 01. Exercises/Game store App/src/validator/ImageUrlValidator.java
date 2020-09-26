package gamestoreapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageUrlValidator implements ConstraintValidator<URL,String> {

    @Override
    public void initialize(URL constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.startsWith("http://") || value.startsWith("https://") || value==null;
    }
}
