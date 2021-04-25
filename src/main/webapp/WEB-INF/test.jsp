<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
        Manage users
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <h2>All customers</h2>
        <p>Pick a customer and show their orders. Afterwards you can check their orderlines</p>
        <p>If a user wants to withdraw money edit their edit balance and click confirm when ready!</p>



        <table>
        <thead><th>Id  </th><th>Email  </th><th>Role  </th><th>Balance  </th><th>Pick order  </th><th>Edit balance  </th></thead>
            <c:forEach var="users" items="${sessionScope.userList}">

                <tr>
                    <form action="${pageContext.request.contextPath}/fc/orders" method="post">
                    <td>${users.id}</td>
                    <td>${users.email}</td>
                    <td>${users.role}</td>
                    <td>${users.balance}</td>
                    <td><button type="submit" name="userid" value="${users.id}" class="btn btn-primary">Orders</button></td>

                    <td><input type="text" name="editbalance" placeholder="Add to current balance"><button type="submit" name="confirm" value="${users.id}" class="btn btn-primary">Confirm</button></td>

                    </form>
                </tr>

            </c:forEach>

            <c:if test="${requestScope.sucess != null}">
                <p style="color: green">${requestScope.sucess}</p>
            </c:if>

        </table>



    </jsp:body>
</t:genericpage>
