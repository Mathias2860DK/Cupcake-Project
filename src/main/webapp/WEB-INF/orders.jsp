<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Manage orders
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.
        <h2>Orders by picked user</h2>
        <p>Pick a order and show it's orderlines</p>



        <table>
            <thead><th>Order Id</th><th>User Id</th><th>Created</th><th>Status</th><th>Pick orderLine</th><th>Delete order and orderlines</th></thead>
            <c:forEach var="orders" items="${sessionScope.orderList}">
                <tr>
                    <form action="${pageContext.request.contextPath}/fc/orderlines" method="post">
                    <td>${orders.orderId}</td>
                    <td>${orders.userId}</td>
                    <td>${orders.created}</td>
                    <td>${orders.status}</td>
                    <td><button type="submit" name="orderid" value="${orders.orderId}" class="btn btn-primary">Orderlines</button></td>
                    <td><button type="submit" name="orderidtodelete" value="${orders.orderId}" class="btn btn-primary">Delete</button></td>
                        <!--Delete knappen skal ikke hen til orderlines siden. Den skal blive på samme side og skrive 'deleted sucessfully' eller lignende.-->
                    </form>
                </tr>
            </c:forEach>

        </table>



    </jsp:body>
</t:genericpage>
