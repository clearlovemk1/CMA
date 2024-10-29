package com.springboot;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    public void addContact(Contact contact) {
        String query = "INSERT INTO contacts (name, address, phone) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getAddress());
            statement.setString(3, contact.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(Contact contact) {
        String query = "UPDATE contacts SET name = ?, address = ?, phone = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
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

    public void deleteContact(int id) {
        String query = "DELETE FROM contacts WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM contacts";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                contacts.add(new Contact(id, name, address, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}

