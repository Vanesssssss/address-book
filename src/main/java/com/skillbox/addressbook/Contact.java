package com.skillbox.addressbook;

import java.util.Locale;

public class Contact {
    private final String fullName;
    private final String position;
    private final String birthDate;
    private final String phoneNumber;
    private final String email;

    public Contact(String fullName, String position, String birthDate, String phoneNumber, String email) {
        this.fullName = fullName.trim().replaceAll("\\s+", " ");
        this.position = position;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Contact contact = (Contact) object;
        String firstName = fullName.toLowerCase(Locale.ROOT);
        String secondName = contact.fullName.toLowerCase(Locale.ROOT);

        return firstName.equals(secondName);
    }

    @Override
    public int hashCode() {
        return fullName.toLowerCase(Locale.ROOT).hashCode();
    }

    @Override
    public String toString() {
        return "ФИО: " + fullName
                + "\nДолжность: " + position
                + "\nДата рождения: " + birthDate
                + "\nНомер телефона: " + phoneNumber
                + "\nЭл. почта: " + email;
    }
}
