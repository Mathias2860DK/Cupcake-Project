<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <h2>Your basket</h2>

<form action="${pageContext.request.contextPath}/fc/manageorderline" method="post">

                <c:forEach var="orderlineItem" items="${sessionScope.orderlineList}">
                    Bottom: ${applicationScope.bottomList.get(orderlineItem.bottomId).bName}<br>
                    Topping: ${applicationScope.toppingList.get(orderlineItem.toppingId).tName}<br>
                    Quantity: ${orderlineItem.quantity}<br>
                    Price: ${orderlineItem.price}
                    <button type="submit" name="delete" value="${orderlineItem.orderlineId}" >Remove</button>
                    <button type="submit" name="edit" value="${orderlineItem.orderlineId}" >Edit</button><br><br>
                </c:forEach>

                <h3>Your total price: ${sessionScope.totalprice} kr. or 0,000070 btc</h3>

</form>
            </div>

            <div class="col-sm-4"></div>
        </div>

        <div>
            <h2>Our Cool Site</h2>
            </div>



            <div style="margin-top: 3em;margin-bottom: 3em;">
                Main page for this 2. semester start project used at cphbusiness.dk
            </div>


            <c:if test="${sessionScope.role == 'employee' }">
                <p style="font-size: larger">This is what you can do,
                    since your are logged in as an employee</p>
                 <p><a href="fc/employeepage">Employee Page</a>
             </c:if>

             <c:if test="${sessionScope.role == 'customer' }">
                <p style="font-size: larger">This is what you can do, since your
                    are logged in as a customer</p>
                <p><a href="fc/customerpage">Customer Page</a>
            </c:if>

        </div>


    </jsp:body>
</t:genericpage>