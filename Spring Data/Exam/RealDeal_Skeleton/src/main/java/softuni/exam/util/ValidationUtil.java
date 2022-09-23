package softuni.exam.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;



public interface ValidationUtil {

    <E> boolean isValid(E entity);
}
