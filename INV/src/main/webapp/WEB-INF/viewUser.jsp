<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zorba.model.User"%>
<%@ page import="com.zorba.dao.UserDAO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Users</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h2>User List</h2>
<table>
    <tr>
        <th>User ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Username</th>
        <th>Roles</th>
    </tr>
    <%
        UserDAO userDao = new UserDAO();
        List<User> users = userDao.getAllUsers();
        for(User user : users) {
    %>
    <tr>
        <td><a href="addRoles.jsp?userId=<%=user.getUserId()%>"><%=user.getUserId()%></a></td>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getMobile()%></td>
        <td><%=user.getUsername()%></td>
        <td>
            <%
                StringJoiner joiner = new StringJoiner(", ");
                for(Role role : user.getRoles()) {
                    joiner.add(role.getRoleName());
                }
                out.print(joiner.toString());
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
