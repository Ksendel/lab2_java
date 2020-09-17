package com.company;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.List;
import static org.junit.Assert.*;

public class StudentTest {

    Student nudeStudent() {
        return new Student();
    }

    @Test
    public void creatValidStudent() {
        String name = "Alex";
        String lastname = "Lev";
        String dateOfBirthday = "01.10.1999";
        String number = "096-1234567";
        Address address = new Address("Peremogi", 20, 1);

        Student student = new Student(name, lastname, dateOfBirthday, number, address);

        assertEquals(student.getName(), name);
        assertEquals(student.getLastname(), lastname);
        assertEquals(student.getDateOfBirthday(), dateOfBirthday);
        assertEquals(student.getNumber(), number);
        assertEquals(student.getHomeAddress(), address);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test()
    public void createInvalidStudent() {
        String name = "A";

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Name can not be empty or less than three characters");

        Student student = new Student();
        student.setName(name);

        exceptionRule = ExpectedException.none();

    }

    @Test
    public void illegalLastName() {
        List.of("", "A", "AB").forEach(s -> {
            try {
                nudeStudent().setLastName(s);
                Assert.fail("Expected IllegalArgumentException");
            } catch (IllegalArgumentException e) {
                assertEquals("Last name can not be empty or less than three characters", e.getMessage());
            }
        });
    }

    @Test
    public void illegalNumber() {
        List.of("", "1234567", "123-123").forEach(s -> {
            try {
                nudeStudent().setNumber(s);
                Assert.fail("Expected IllegalArgumentException");
            } catch (IllegalArgumentException e) {
                assertEquals("Your phone number must be in the form XXX-XXXXXXX", e.getMessage());
            }
        });
    }

    @Test
    public void illegalDate() {
        List.of("20010101", "2001.01.21").forEach(s -> {
            try {
                nudeStudent().setDateOfBirthday(s);
                Assert.fail("Expected IllegalArgumentException");
            } catch (IllegalArgumentException e) {
                assertEquals("Your date of birthday must be in the form MM/dd/yyyy", e.getMessage());
            }
        });
    }
}