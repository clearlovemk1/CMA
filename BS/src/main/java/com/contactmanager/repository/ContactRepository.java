package com.contactmanager.repository;

import com.contactmanager.model.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/contactdb?useSSL=false"; // 替换为实际数据库名称
    private final String jdbcUsername = "root"; // 替换为实际数据库用户名
    private final String jdbcPassword = "Kimi.2"; // 替换为实际数据库密码

    // 获取数据库连接
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
    }

    // 添加联系人
    public void addContact(Contact contact) {
        String query = "INSERT INTO contacts (name, address, phone) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getAddress());
            statement.setString(3, contact.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据ID删除联系人
    public void deleteContactById(int id) {
        String query = "DELETE FROM contacts WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新联系人信息
    public void updateContact(Contact contact) {
        String query = "UPDATE contacts SET name = ?, address = ?, phone = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getAddress());
            statement.setString(3, contact.getPhone());
            statement.setInt(4, contact.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据姓名查询联系人
    public List<Contact> findContactsByName(String name) {
        String query = "SELECT * FROM contacts WHERE name = ?";
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // 获取所有联系人
    public List<Contact> getAllContacts() {
        String query = "SELECT * FROM contacts";
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
