package com.contactmanager.service;

import com.contactmanager.model.Contact;
import com.contactmanager.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    // 构造函数，初始化 ContactRepository
    public ContactService() {
        this.contactRepository = new ContactRepository();
    }

    // 添加联系人
    public void addContact(Contact contact) {
        // 可以在此处添加业务逻辑校验，例如检查姓名是否为空等
        if (contact.getName() == null || contact.getName().isEmpty()) {
            throw new IllegalArgumentException("联系人姓名不能为空");
        }
        contactRepository.addContact(contact);
    }

    // 删除联系人
    public void deleteContactById(int id) {
        // 业务逻辑校验，如 ID 是否有效
        if (id <= 0) {
            throw new IllegalArgumentException("联系人 ID 无效");
        }
        contactRepository.deleteContactById(id);
    }

    // 更新联系人信息
    public void updateContact(Contact contact) {
        // 校验更新内容的有效性
        if (contact.getId() <= 0) {
            throw new IllegalArgumentException("联系人 ID 无效");
        }
        if (contact.getName() == null || contact.getName().isEmpty()) {
            throw new IllegalArgumentException("联系人姓名不能为空");
        }
        contactRepository.updateContact(contact);
    }

    // 按姓名查找联系人
    public List<Contact> findContactsByName(String name) {
        // 可以在此添加校验逻辑，例如是否为空
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("查询的姓名不能为空");
        }
        return contactRepository.findContactsByName(name);
    }

    // 获取所有联系人
    public List<Contact> getAllContacts() {
        return contactRepository.getAllContacts();
    }
}
