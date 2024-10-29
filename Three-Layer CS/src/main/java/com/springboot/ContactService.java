package com.springboot;



import java.util.List;

public class ContactService {
    private final ContactDAO contactDAO;

    public ContactService() {
        this.contactDAO = new ContactDAO();
    }

    // 添加联系人
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    // 更新联系人信息
    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }

    // 删除联系人
    public void deleteContact(int id) {
        contactDAO.deleteContact(id);
    }

    // 获取所有联系人
    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }

    // 根据ID获取联系人
    public Contact getContactById(int id) {
        return contactDAO.getContactById(id);
    }

    public List<Contact> getContactsByName(String name) {
        return contactDAO.getContactsByName(name);
    }


}


