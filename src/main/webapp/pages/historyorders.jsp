<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html; charset=UTF-8" language = "java" %>
<html>
    <head>
        <title>История заказов</title>
        <style>
            <%@include file='../libs/bootstrap/css/bootstrap.min.css' %>
            <%@include file='../libs/animate/animate.css' %>
            <%@include file='../css/fonts.css' %>
            <%@include file='../css/media.css' %>
            <%@include file='../css/main.css' %>
        </style>
        <link href="../css/fonts.css" type="text/css">
    <meta charset="utf-8">
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> 
    </head>
    <body>
        <center>
            <div style="padding-left: 5%;
                padding-right: 5%;">
        <div class="navbar navbar-inverse">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
                        <span class="sr-only">Открыть навигацию</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/restaurant">Elite</a>
                </div>
                <div class="collapse navbar-collapse" id="responsive-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="/restaurant/menu">Меню</a></li>
                        <li><a href="/restaurant/dish">Блюдо</a></li>
                        <li><a href="/restaurant/employee">Персонал</a></li>
                        <li><a href="/restaurant/store">Склад</a></li>
                        <li><a href="/restaurant/order">История заказов</a></li>
                    </ul>
                </div>
            </div>
                <form action="" method="post">
                    <b>Фильтровать по официанту принявшего заказ:</b>
                    Имя официанта:<input type="text" name="name">
                    Фамилия официанта:<input type="text" name="surname"><br>
                    <b>Фильтровать по номеру столика:</b><input type="text" name="number_table"><br>
                    <b>Фильтровать по дате:</b><input type="text" name="date"><br><input type="submit" value="Фильтровать"><br>
                </form>
                <h1 class="style-color">Список заказов:</h1>
                <b style="color: red;">${error}</b>
                <table class="style-color" border="1px">
                    <tr>
                        <th>Номер заказа</th>
                        <th>Официант принявший заказ</th>
                        <th>Список блюд</th>
                        <th>Номер стола</th>
                        <th>Дата заказа</th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.getId()}</td>
                            <td>${order.getIdEmployee().getName()} ${order.getIdEmployee().getSurname()}</td>
                            <td>
                                <c:set var="dishes" value="${order.getDishList()}"/>
                                <c:forEach items="${dishes}" var="dishResult">
                                    ${dishResult.getName()}<br>
                                </c:forEach>
                            </td>
                            <td>${order.getNumberTable()}</td>
                            <td>${order.getDate()}</td>
                            
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </center>  
    </body>
</html>    