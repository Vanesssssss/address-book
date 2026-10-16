package com.skillbox.addressbook;

import java.util.Locale;

public class AddressBook {
    private static final int MAX_CONTACTS = 100;
    private static final int NOT_FOUND = -1;

    private final Contact[] contacts = new Contact[MAX_CONTACTS];

    public int findContactIndex(Contact contact) {
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] != null && contacts[i].equals(contact)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public boolean addContact(Contact contact) {
        int index = findContactIndex(contact);

        if (index != NOT_FOUND) {
            contacts[index] = contact;
            return true;
        }

        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] == null) {
                contacts[i] = contact;
                return true;
            }
        }
        return false;
    }

    public boolean removeContact(String id) {
        for (int i = 0; i < contacts.length; i++) {
            if (String.valueOf(i).equals(id) && contacts[i] != null) {
                contacts[i] = null;
                return true;
            }
        }
        return false;
    }

    public String searchByName(String query) {
        String normalizedQuery = query.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
        if (normalizedQuery.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] != null
                    && contacts[i].getFullName().toLowerCase(Locale.ROOT).contains(normalizedQuery)) {
                appendContact(result, i);
            }
        }
        return result.toString();
    }

    private void appendContact(StringBuilder result, int id) {
        result.append("ID: ").append(id).append('\n')
                .append(contacts[id]).append("\n\n");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] != null) {
                appendContact(result, i);
            }
        }
        return result.toString();
    }
}
