<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Role</title>
</head>
<body>
<h2>Add Role</h2>
<form action="AddRoleServlet" method="post">
    <input type="hidden" name="userId" value="<%=request.getParameter("userId")%>">
    <select name="roleName" required>
        <option value="">Select Role</option>
        <option value="ADMIN">ADMIN</option>
        <option value="USER">USER</option>
        <option value="VENDOR">VENDOR</option>
    </select>
    <input type="submit" value="Add Role">
</form>
</body>
</html>
