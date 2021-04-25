<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Demo Page for Customer Roles
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        You are now logged in as a ${sessionScope.role} of our site. <br>
       You can now add cupcakes to your basket and order them!
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/index">Click here to browse our cupcakes!</a>

    </jsp:body>

</t:genericpage>

