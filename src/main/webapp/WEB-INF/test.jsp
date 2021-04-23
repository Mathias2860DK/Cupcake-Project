<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Demo Page for Employee Roles
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.
        <h2>All customers</h2>
        <p>Pick a customer and show orders and orderlines</p>



        <table>
        <thead><th>Id</th><th>Email</th><th>Role</th><th>Balance</th></thead>
            <c:forEach var="users" items="${requestScope.userList}">
                <tr><td>${users.id}</td>
                    <td>${users.email}</td>
                    <td>${users.role}</td>
                    <td>${users.balance}</td>
                </tr>
            </c:forEach>

        </table>



    </jsp:body>
</t:genericpage>
