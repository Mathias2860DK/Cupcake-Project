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
        <h2>Orders by picked user</h2>
        <p>Pick a order and show it's orderlines</p>



        <table>
            <thead><th>Orderline ID</th><th>Order id</th><th>Quantity</th><th>Price</th><th>Topping ID</th><th>Bottom ID</th></thead>
            <c:forEach var="orderline" items="${sessionScope.orderlineList}">
                <tr>
                    <td>${orderline.orderlineId}</td>
                    <td>${orderline.orderId}</td>
                    <td>${orderline.quantity}</td>
                    <td>${orderline.price}</td>
                    <td>${orderline.toppingId}</td>
                    <td>${orderline.bottomId}</td>

                </tr>
            </c:forEach>

        </table>



    </jsp:body>
</t:genericpage>
