package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.*;

public class StudentTest {
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
    public void creatInvalidStudent() {
        String name = "Al";

        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("Name can not be empty or less than three characters");

        Student student = new Student();
        student.setName(name);

        exceptionRule = ExpectedException.none();

    }
}