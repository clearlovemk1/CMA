package com.springboot;


import java.util.List;
import java.util.Scanner;

public class ContactManagerApp {
    private static final ContactDAO contactDAO = new ContactDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("选择操作：");
            System.out.println("1. 添加联系人");
            System.out.println("2. 修改联系人");
            System.out.println("3. 删除联系人");
            System.out.println("4. 显示所有联系人");
            System.out.println("5. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (choice) {
                case 1:
                    System.out.print("姓名：");
                    String name = scanner.nextLine();
                    System.out.print("地址：");
                    String address = scanner.nextLine();
                    System.out.print("电话：");
                    String phone = scanner.nextLine();
                    contactDAO.addContact(new Contact(0, name, address, phone));
                    break;
                case 2:
                    System.out.print("输入要修改的联系人 ID：");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // 清除换行符
                    System.out.print("新姓名：");
                    String newName = scanner.nextLine();
                    System.out.print("新地址：");
                    String newAddress = scanner.nextLine();
                    System.out.print("新电话：");
                    String newPhone = scanner.nextLine();
                    contactDAO.updateContact(new Contact(idToUpdate, newName, newAddress, newPhone));
                    break;
                case 3:
                    System.out.print("输入要删除的联系人 ID：");
                    int idToDelete = scanner.nextInt();
                    contactDAO.deleteContact(idToDelete);
                    break;
                case 4:
                    List<Contact> contacts = contactDAO.getAllContacts();
                    for (Contact contact : contacts) {
                        System.out.println("ID: " + contact.getId() + ", 姓名: " + contact.getName() +
                                ", 地址: " + contact.getAddress() + ", 电话: " + contact.getPhone());
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效选择，请重试。");
            }
        }
    }
}

