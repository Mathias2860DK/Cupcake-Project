<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Manage orderlines
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Orderlines by order </h1>





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
<!--Der skal være en tilbage knap eller en knap der hedder slet hele orderen-->


    </jsp:body>
</t:genericpage>
