package com.contactmanager.controller;

import com.contactmanager.model.Contact;
import com.contactmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // 添加联系人
    @PostMapping("/add")
    public String addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
        return "联系人添加成功！";
    }

    // 删除联系人
    @DeleteMapping("/delete/{id}")
    public String deleteContactById(@PathVariable int id) {
        contactService.deleteContactById(id);
        return "联系人删除成功！";
    }

    // 更新联系人信息
    @PutMapping("/update")
    public String updateContact(@RequestBody Contact contact) {
        contactService.updateContact(contact);
        return "联系人更新成功！";
    }

    // 按姓名查找联系人
    @GetMapping("/search")
    public List<Contact> findContactsByName(@RequestParam String name) {
        return contactService.findContactsByName(name);
    }

    // 获取所有联系人
    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
}
