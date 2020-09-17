package com.company;
import java.util.*;
//import javax.validation.constraints.Min;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

    private int id;
    private String name;
    private String lastname;
    private String dateOfBirthday;
    private String number;
    private Address homeAddress;

    private static Map<Integer, Student> allStudents;

    private static int countId = 0;

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, dateOfBirthday, number, homeAddress);
    }

    private boolean hasStudent() {
        for (Student student : allStudents.values()) {
            if (student.equals(this) && student.hashCode() == this.hashCode()) {
                return true;
            }
        }
        return false;
    }

    public Student() {
        if (allStudents == null) {
            allStudents = new HashMap<>();
        }

        if (!hasStudent()) {
            countId++;
            this.id = countId;
            allStudents.put(id, this);
        }
    }

    public Student(String name, String lastname, String dateOfBirthday, String number, Address homeAddress) {
        setName(name);
        setLastname(lastname);
        this.dateOfBirthday = dateOfBirthday;
        setNumber(number);
        this.homeAddress = homeAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkEmptyOrDie("Name", name);
        this.name = capitalize(name);
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        checkEmptyOrDie("Lastname", lastname);
        this.lastname = capitalize(lastname);
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        patternDate(dateOfBirthday);
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        patternNumber(number);
        this.number = number;

    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    void checkEmptyOrDie(String fieldName, String value) {

        if(value == null || value.isEmpty() || value.length() < 3)
            throw new IllegalArgumentException(String.format("%s can not be empty or less than three characters", fieldName));
    }

    String capitalize(String word) {
        return word == null || word.isEmpty() ? word: word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    void patternNumber(String nums) {
        Pattern pattern = Pattern.compile("^\\d{3}-\\d{7}$");
        Matcher matcher = pattern.matcher(nums);

        if (nums.isEmpty() || !matcher.matches()) {
            throw new IllegalArgumentException("Your phone number must be in the form XXX-XXXXXXX");
//            System.out.println("Phone Number must be in the form XXX-XXXXXXX");
        }

    }

    void patternDate(String dateOfBirthday) {
        String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateOfBirthday);

        if (dateOfBirthday.isEmpty() || !matcher.matches()) {
            throw new IllegalArgumentException("Your date of birthday must be in the form MM/dd/yyyy");
        }
    }

    @Override
    public String toString() {
        return "Student: " +
                "\nid: " + id +
                ",\nname: " + name +
                ",\nlastname: " + lastname +
                ",\ndate of birthday: " + dateOfBirthday +
                ",\nnumber: " + number +
                ",\nhome address: " +"str."+ homeAddress.street +
                "/№" + homeAddress.homeNumber +
                "/" + homeAddress.flatNumber;
    }

    public static List<Student> getAllStudent() {
        return new ArrayList<>(allStudents.values());
    }

}
