package com.example.springmappingexar.utils;

import javax.validation.ConstraintViolation;
import java.util.Set;


public interface ValidationUtil {
   <T> Set<ConstraintViolation<T>> violation(T entity);
}
