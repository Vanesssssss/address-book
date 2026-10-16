package com.skillbox.addressbook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {
    private Contact contact(String name) {
        return new Contact(name, "Developer", "01.01.2000", "+375 29 000-00-00", "test@example.com");
    }

    @Test
    void replacesContactWithSameNormalizedName() {
        AddressBook book = new AddressBook();
        Contact original = contact("Иван   Иванов");
        Contact updated = new Contact(" иВаН Иванов ", "Senior Developer", "01.01.2000", "+375 29 111-11-11", "new@example.com");

        assertTrue(book.addContact(original));
        assertEquals(0, book.findContactIndex(updated));
        assertTrue(book.addContact(updated));
        assertTrue(book.toString().contains("Senior Developer"));
        assertFalse(book.toString().contains("+375 29 000-00-00"));
    }

    @Test
    void removalFreesTheSameIdForReuse() {
        AddressBook book = new AddressBook();
        assertTrue(book.addContact(contact("Иван Иванов")));
        assertTrue(book.removeContact("0"));
        assertFalse(book.removeContact("0"));
        assertTrue(book.addContact(contact("Анна Петрова")));
        assertTrue(book.toString().contains("ID: 0"));
    }

    @Test
    void rejectsNewContactWhenFullButCanReplaceExistingOne() {
        AddressBook book = new AddressBook();
        for (int i = 0; i < 100; i++) {
            assertTrue(book.addContact(contact("Контакт " + i)));
        }

        assertFalse(book.addContact(contact("Лишний контакт")));
        assertTrue(book.addContact(contact("КОНТАКТ 42")));
        assertTrue(book.removeContact("42"));
        assertTrue(book.addContact(contact("Лишний контакт")));
    }
}
