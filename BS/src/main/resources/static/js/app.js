// 获取所有联系人并展示
function fetchContacts() {
    fetch('/api/contacts/all')
        .then(response => response.json())
        .then(data => {
            const contactsDiv = document.getElementById('contacts-list');
            contactsDiv.innerHTML = ''; // 清空当前内容
            data.forEach(contact => {
                const contactItem = document.createElement('div');
                contactItem.textContent = `ID: ${contact.id}, 姓名: ${contact.name}, 地址: ${contact.address}, 电话: ${contact.phone}`;
                contactsDiv.appendChild(contactItem);
            });
        })
        .catch(error => console.error('获取联系人失败:', error));
}

// 添加联系人
function addContact() {
    const name = document.getElementById('add-name').value;
    const address = document.getElementById('add-address').value;
    const phone = document.getElementById('add-phone').value;

    fetch('/api/contacts/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, address, phone })
    })
        .then(response => response.text())
        .then(message => {
            alert(message);
            fetchContacts(); // 重新加载联系人列表
        })
        .catch(error => console.error('添加联系人失败:', error));
}

// 更新联系人
function updateContact() {
    const id = parseInt(document.getElementById('update-id').value);
    const name = document.getElementById('update-name').value;
    const address = document.getElementById('update-address').value;
    const phone = document.getElementById('update-phone').value;

    fetch('/api/contacts/update', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id, name, address, phone })
    })
        .then(response => response.text())
        .then(message => {
            alert(message);
            fetchContacts(); // 重新加载联系人列表
        })
        .catch(error => console.error('更新联系人失败:', error));
}

// 删除联系人
function deleteContact() {
    const id = parseInt(document.getElementById('delete-id').value);

    fetch(`/api/contacts/delete/${id}`, { method: 'DELETE' })
        .then(response => response.text())
        .then(message => {
            alert(message);
            fetchContacts(); // 重新加载联系人列表
        })
        .catch(error => console.error('删除联系人失败:', error));
}

// 按姓名查询联系人
function searchContacts() {
    const name = document.getElementById('search-name').value;

    fetch(`/api/contacts/search?name=${encodeURIComponent(name)}`)
        .then(response => response.json())
        .then(data => {
            const searchResultsDiv = document.getElementById('search-results');
            searchResultsDiv.innerHTML = ''; // 清空当前内容
            if (data.length === 0) {
                searchResultsDiv.textContent = '未找到匹配的联系人';
            } else {
                data.forEach(contact => {
                    const contactItem = document.createElement('div');
                    contactItem.textContent = `ID: ${contact.id}, 姓名: ${contact.name}, 地址: ${contact.address}, 电话: ${contact.phone}`;
                    searchResultsDiv.appendChild(contactItem);
                });
            }
        })
        .catch(error => console.error('查询联系人失败:', error));
}
