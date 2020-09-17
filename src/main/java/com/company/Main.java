package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of new students");
        int num = in.nextInt();
        for (int i = 0; i < num; i++) {
            Student st = new Student();
            System.out.println("Enter the name: ");
            askWithRetry(() -> st.setName(in.next()));
            System.out.println("Enter the lastname: ");
            askWithRetry(() -> st.setLastname(in.next()));
            System.out.println("Enter the date Of birthday: ");
            askWithRetry(() -> st.setDateOfBirthday(in.next()));

            System.out.println("Enter the number: ");
            askWithRetry(() -> st.setNumber(in.next()));

            System.out.println("Enter the street address: ");
            String street = in.next();
            System.out.println("Enter the home number: ");
            int homeNumber = in.nextInt();
            System.out.println("Enter the flat number: ");
            int flatNumber = in.nextInt();
            st.setHomeAddress(new Address(street, homeNumber, flatNumber));
        }

        System.out.println("List of students: ");
        Student.getAllStudent().forEach(System.out::println);
    }

    static void askWithRetry(Runnable action) {
        boolean isFieldInvalid = true;
        while (isFieldInvalid) {
            try {
                action.run();
                isFieldInvalid = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
