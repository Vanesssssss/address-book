package com.skillbox.addressbook;

import java.util.Scanner;

public class MenuNavigator {
    private final Scanner scanner = new Scanner(System.in);
    private final AddressBook addressBook = new AddressBook();

    private boolean running;

    public void start() {
        running = true;

        while (running) {
            int option = selectMainMenu();

            switch (option) {
                case 1:
                    newContact();
                    break;
                case 2:
                    removeContact();
                    break;
                case 3:
                    printContacts();
                    break;
                case 4:
                    searchContacts();
                    break;
                case 5:
                    exit();
                    break;
            }
        }
    }

    private void printContacts() {
        System.out.println("Список контактов: ");
        System.out.print(addressBook);
    }

    private void searchContacts() {
        System.out.print("Введите имя или его часть: ");
        String matches = addressBook.searchByName(scanner.nextLine());
        if (matches.isEmpty()) {
            System.out.println("Контакты не найдены\n");
        } else {
            System.out.print(matches);
        }
    }

    private void removeContact() {
        System.out.print("Введите ID контакта для удаления: ");
        String id = scanner.nextLine();

        if (addressBook.removeContact(id)) {
            System.out.println("Контакт с ID: " + id + " удалён\n");
        } else {
            System.out.println("Контакт с ID: " + id + " не найден\n");
        }
    }

    private void newContact() {
        System.out.print("Введите ФИО контакта: ");
        String fullName = scanner.nextLine();

        while (fullName.isEmpty()) {
            System.out.println("ФИО не может быть пустым");
            System.out.print("Введите ФИО контакта: ");
            fullName = scanner.nextLine();
        }

        System.out.print("Должность: ");
        String position = scanner.nextLine();

        System.out.print("Дата рождения: ");
        String birthDate = scanner.nextLine();

        System.out.print("Номер телефона: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Эл. почта: ");
        String email = scanner.nextLine();

        Contact contact = new Contact(fullName, position, birthDate, phoneNumber, email);

        int index = addressBook.findContactIndex(contact);
        if (index >= 0) {
            System.out.println("Пользователь " + contact.getFullName() + " уже существует, он будет обновлён");
        }

        if (!addressBook.addContact(contact)) {
            System.out.println("Адресная книга заполнена\n");
        } else if (index != -1) {
            System.out.println("Контакт был обновлён\n");
        } else {
            System.out.println("Новый контакт добавлен\n");
        }
    }

    private void exit() {
        running = false;
    }

    private int selectMainMenu() {
        System.out.println("Адресная книга");
        System.out.println("1 - добавить/обновить контакт");
        System.out.println("2 - удалить контакт");
        System.out.println("3 - список контактов");
        System.out.println("4 - поиск по имени");
        System.out.println("5 - выход");
        System.out.print("Введите 1..5: ");
        String input = scanner.nextLine();

        for (int option = 1; option <= 5; option++) {
            if (String.valueOf(option).equals(input)) {
                return option;
            }
        }

        System.out.println("Выбрана неверная опция. Попробуйте заново");
        return 0;
    }
}
