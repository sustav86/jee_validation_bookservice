package com.sustav.book;/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sustav.book.entity.Customer;

public class AppTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void shouldRaiseNoConstraintViolation() {
        Customer customer = new Customer("John", "Smith", "jsmith@gmail.com");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidEmail() {
        Customer customer = new Customer("Джон", "Смит", "DummyEmail");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
        assertEquals("invalid email address", violations.iterator().next().
                getMessage());
        assertEquals("dummy", violations.iterator().next().getInvalidValue());
        assertEquals("{org.agoncal.book.javaee7.chapter03.Email.message}",
                violations.iterator().next().getMessageTemplate());
    }
}