package com.springboot;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "Kimi.2";

    // 获取数据库连接
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 添加联系人
    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts (name, address, phone) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getAddress());
            stmt.setString(3, contact.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新联系人信息
    public void updateContact(Contact contact) {
        String sql = "UPDATE contacts SET name = ?, address = ?, phone = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getAddress());
            stmt.setString(3, contact.getPhone());
            stmt.setInt(4, contact.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除联系人
    public void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取所有联系人
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                contacts.add(new Contact(id, name, address, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // 根据ID获取联系人
    public Contact getContactById(int id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    return new Contact(id, name, address, phone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contact> getContactsByName(String name) {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts WHERE name = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    contacts.add(new Contact(id, name, address, phone));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

}


