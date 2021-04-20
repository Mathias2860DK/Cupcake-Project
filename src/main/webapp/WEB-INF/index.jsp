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

        <div>
            <h2>Our Cool Site</h2>

            <div
                    id="carouselExampleControls"
                    class="carousel slide"
                    data-ride="carousel"
            >
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img
                                class="d-block w-100"
                                src="${pageContext.request.contextPath}/css/images/LoveAtFirstBite.jpg"
                                alt="First slide"
                        />
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100"
                             src="${pageContext.request.contextPath}/css/images/cupcakeShop.jpg"
                             alt="Second slide"
                        />
                    </div>
                    <div class="carousel-item">
                        <img
                                class="d-block w-100"
                                src="${pageContext.request.contextPath}/css/images/sliderCupcake.png"
                                alt="Third slide"
                        />
                    </div>
                </div>
                <a
                        class="carousel-control-prev"
                        href="#carouselExampleControls"
                        role="button"
                        data-bs-slide="prev"
                >
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Forrige</span>
                </a>
                <a
                        class="carousel-control-next"
                        href="#carouselExampleControls"
                        role="button"
                        data-bs-slide="next"
                >
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Næste</span>
                </a>
            </div>



            <div style="margin-top: 3em;margin-bottom: 3em;">
                Main page for this 2. semester start project used at cphbusiness.dk
            </div>

            <form method="post" action="${pageContext.request.contextPath}/fc/addtobasket">

                <label for="bottom">Vælg din bund:</label>
                <select name="bottom" id="bottom">
                    <c:forEach var="bottom" items="${applicationScope.bottomList}">
                        <option value="${bottom.bottomId}">${bottom.bName} ... ${bottom.price} kr.</option>

                    </c:forEach>
                </select>

                <label for="topping">Vælg din topping:</label>
                <select name="topping" id="topping">
                    <c:forEach var="topping" items="${applicationScope.toppingList}">
                        <option value="${topping.toppingId}">${topping.tName} ... ${topping.price} kr.</option>

                    </c:forEach>

                </select>
                <label for ="quantity">Vælg antal </label>
                <select name="quantity" id="quantity">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>

                </select>



                <button type="submit" class="btn btn-primary">Tilføj til kurv</button>

                <c:if test="${requestScope.error != null}">
                    <p style="color: red">${requestScope.error}</p>
                </c:if>


                <div class="col-sm-4"></div>
            </form>

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