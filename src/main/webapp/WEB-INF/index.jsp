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
            <h2>Order your cupcakes here!</h2>

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
                Customize your own cupcake! NOTE: You have to be logged in to add cupcakes to your basket
            </div>

            <form method="post" action="${pageContext.request.contextPath}/fc/addtobasket">

                <label for="bottom">Choose your bottom:</label>
                <select name="bottom" id="bottom">
                    <c:forEach var="bottom" items="${applicationScope.bottomList}">
                        <option value="${bottom.bottomId}">${bottom.bName} ... ${bottom.price} DKK.</option>

                    </c:forEach>
                </select>

                <label for="topping">Choose your topping:</label>
                <select name="topping" id="topping">
                    <c:forEach var="topping" items="${applicationScope.toppingList}">
                        <option value="${topping.toppingId}">${topping.tName} ... ${topping.price} DKK.</option>

                    </c:forEach>

                </select>
                <label for ="quantity">Quantity:</label>
                <select name="quantity" id="quantity">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>

                </select>



                <button type="submit" class="btn btn-primary">Add to basket</button>

                <c:if test="${requestScope.error != null}">
                    <p style="color: red">${requestScope.error}</p>
                </c:if>


                <div class="col-sm-4"></div>
            </form>

        </div>


    </jsp:body>
</t:genericpage>