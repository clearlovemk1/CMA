<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contacts</title>
</head>
<body>
<h1>Contacts</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="contact" items="${contacts}">
        <tr>
            <td>${contact.id}</td>
            <td>${contact.name}</td>
            <td>${contact.address}</td>
            <td>${contact.phone}</td>
            <td>
                <form action="/contacts/update" method="post">
                    <input type="hidden" name="id" value="${contact.id}"/>
                    <input type="text" name="name" value="${contact.name}" />
                    <input type="text" name="address" value="${contact.address}" />
                    <input type="text" name="phone" value="${contact.phone}" />
                    <button type="submit">Update</button>
                </form>
                <form action="/contacts/delete" method="post">
                    <input type="hidden" name="id" value="${contact.id}" />
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Add New Contact</h2>
<form action="/contacts/add" method="post">
    <input type="text" name="name" placeholder="Name" />
    <input type="text" name="address" placeholder="Address" />
    <input type="text" name="phone" placeholder="Phone" />
    <button type="submit">Add Contact</button>
</form>
</body>
</html>
