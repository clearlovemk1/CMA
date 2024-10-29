package com.springboot;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContactUI {
    private final ContactService contactService;
    private JFrame frame;

    public ContactUI() {
        this.contactService = new ContactService();
        initUI();
    }

    private void initUI() {
        frame = new JFrame("个人通讯录管理系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        displayMenu(panel);

        frame.setVisible(true);
    }

    private void displayMenu(JPanel panel) {
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton addButton = new JButton("添加联系人");
        JButton updateButton = new JButton("更新联系人");
        JButton deleteButton = new JButton("删除联系人");
        JButton viewButton = new JButton("查看所有联系人");
        JButton searchButton = new JButton("查找联系人");

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewButton);
        panel.add(searchButton);

        // 添加联系人事件
        addButton.addActionListener(e -> showAddContactDialog());

        // 更新联系人事件
        updateButton.addActionListener(e -> showUpdateContactDialog());

        // 删除联系人事件
        deleteButton.addActionListener(e -> showDeleteContactDialog());

        // 查看所有联系人事件
        viewButton.addActionListener(e -> showAllContactsDialog());

        // 查找联系人事件
        searchButton.addActionListener(e -> showSearchContactDialog());
    }

    // 添加联系人对话框
    private void showAddContactDialog() {
        JTextField nameField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JTextField phoneField = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("姓名:"));
        panel.add(nameField);
        panel.add(new JLabel("地址:"));
        panel.add(addressField);
        panel.add(new JLabel("电话:"));
        panel.add(phoneField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "添加联系人", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Contact contact = new Contact(0, nameField.getText(), addressField.getText(), phoneField.getText());
            contactService.addContact(contact);
            JOptionPane.showMessageDialog(frame, "联系人已添加！");
        }
    }

    // 更新联系人对话框
    private void showUpdateContactDialog() {
        String name = JOptionPane.showInputDialog(frame, "请输入要更新的联系人姓名:");
        if (name != null && !name.trim().isEmpty()) {
            List<Contact> contacts = contactService.getContactsByName(name);
            if (contacts.size() > 1) {
                StringBuilder result = new StringBuilder("找到多个联系人，请指定ID:\n");
                for (Contact contact : contacts) {
                    result.append("ID: ").append(contact.getId())
                            .append(", 姓名: ").append(contact.getName())
                            .append(", 地址: ").append(contact.getAddress())
                            .append(", 电话: ").append(contact.getPhone()).append("\n");
                }
                String idStr = JOptionPane.showInputDialog(frame, new JTextArea(result.toString()), "指定联系人ID", JOptionPane.QUESTION_MESSAGE);
                if (idStr != null && !idStr.trim().isEmpty()) {
                    int id = Integer.parseInt(idStr.trim());
                    Contact contactToUpdate = contactService.getContactById(id);
                    if (contactToUpdate != null) {
                        JTextField nameField = new JTextField(contactToUpdate.getName(), 20);
                        JTextField addressField = new JTextField(contactToUpdate.getAddress(), 20);
                        JTextField phoneField = new JTextField(contactToUpdate.getPhone(), 20);

                        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
                        panel.add(new JLabel("姓名:"));
                        panel.add(nameField);
                        panel.add(new JLabel("地址:"));
                        panel.add(addressField);
                        panel.add(new JLabel("电话:"));
                        panel.add(phoneField);

                        int resultUpdate = JOptionPane.showConfirmDialog(frame, panel, "更新联系人", JOptionPane.OK_CANCEL_OPTION);
                        if (resultUpdate == JOptionPane.OK_OPTION) {
                            contactToUpdate.setName(nameField.getText());
                            contactToUpdate.setAddress(addressField.getText());
                            contactToUpdate.setPhone(phoneField.getText());
                            contactService.updateContact(contactToUpdate);
                            JOptionPane.showMessageDialog(frame, "联系人已更新！");
                        }
                    }
                }
            } else if (contacts.size() == 1) {
                // 仅一个结果时直接更新
                Contact contactToUpdate = contacts.get(0);
                JTextField nameField = new JTextField(contactToUpdate.getName(), 20);
                JTextField addressField = new JTextField(contactToUpdate.getAddress(), 20);
                JTextField phoneField = new JTextField(contactToUpdate.getPhone(), 20);

                JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
                panel.add(new JLabel("姓名:"));
                panel.add(nameField);
                panel.add(new JLabel("地址:"));
                panel.add(addressField);
                panel.add(new JLabel("电话:"));
                panel.add(phoneField);

                int resultUpdate = JOptionPane.showConfirmDialog(frame, panel, "更新联系人", JOptionPane.OK_CANCEL_OPTION);
                if (resultUpdate == JOptionPane.OK_OPTION) {
                    contactToUpdate.setName(nameField.getText());
                    contactToUpdate.setAddress(addressField.getText());
                    contactToUpdate.setPhone(phoneField.getText());
                    contactService.updateContact(contactToUpdate);
                    JOptionPane.showMessageDialog(frame, "联系人已更新！");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "未找到指定姓名的联系人！");
            }
        }
    }


    // 删除联系人对话框
    private void showDeleteContactDialog() {
        String name = JOptionPane.showInputDialog(frame, "请输入要删除的联系人姓名:");
        if (name != null && !name.trim().isEmpty()) {
            List<Contact> contacts = contactService.getContactsByName(name);
            if (contacts.size() > 1) {
                StringBuilder result = new StringBuilder("找到多个联系人，请指定ID:\n");
                for (Contact contact : contacts) {
                    result.append("ID: ").append(contact.getId())
                            .append(", 姓名: ").append(contact.getName())
                            .append(", 地址: ").append(contact.getAddress())
                            .append(", 电话: ").append(contact.getPhone()).append("\n");
                }
                String idStr = JOptionPane.showInputDialog(frame, new JTextArea(result.toString()), "指定联系人ID", JOptionPane.QUESTION_MESSAGE);
                if (idStr != null && !idStr.trim().isEmpty()) {
                    int id = Integer.parseInt(idStr.trim());
                    contactService.deleteContact(id);
                    JOptionPane.showMessageDialog(frame, "联系人已删除！");
                }
            } else if (contacts.size() == 1) {
                // 仅一个结果时直接删除
                int resultDelete = JOptionPane.showConfirmDialog(frame, "确定删除联系人：" + contacts.get(0).getName() + " 吗？", "删除联系人", JOptionPane.YES_NO_OPTION);
                if (resultDelete == JOptionPane.YES_OPTION) {
                    contactService.deleteContact(contacts.get(0).getId());
                    JOptionPane.showMessageDialog(frame, "联系人已删除！");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "未找到指定姓名的联系人！");
            }
        }
    }


    // 查看所有联系人对话框
    private void showAllContactsDialog() {
        List<Contact> contacts = contactService.getAllContacts();
        StringBuilder message = new StringBuilder("所有联系人信息:\n\n");
        for (Contact contact : contacts) {
            message.append("ID: ").append(contact.getId()).append(", 姓名: ").append(contact.getName())
                    .append(", 地址: ").append(contact.getAddress()).append(", 电话: ").append(contact.getPhone()).append("\n");
        }
        JTextArea textArea = new JTextArea(message.toString());
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "联系人列表", JOptionPane.INFORMATION_MESSAGE);
    }

    // 查找联系人对话框
    private void showSearchContactDialog() {
        String name = JOptionPane.showInputDialog(frame, "请输入要查找的联系人姓名:");
        if (name != null && !name.trim().isEmpty()) {
            List<Contact> contacts = contactService.getContactsByName(name);
            if (!contacts.isEmpty()) {
                StringBuilder result = new StringBuilder("查找到以下联系人:\n");
                for (Contact contact : contacts) {
                    result.append("ID: ").append(contact.getId())
                            .append(", 姓名: ").append(contact.getName())
                            .append(", 地址: ").append(contact.getAddress())
                            .append(", 电话: ").append(contact.getPhone()).append("\n");
                }
                JTextArea textArea = new JTextArea(result.toString());
                textArea.setEditable(false);
                JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "查找结果", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "未找到指定姓名的联系人！");
            }
        }
    }

}


