<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html; charset=UTF-8" language = "java" %>
<html>
    <head>   
        <title>Привет клиент!</title>
        <style>
            <%@include file='../../libs/bootstrap/css/bootstrap.min.css' %>
            <%@include file='../../libs/animate/animate.css' %>
            <%@include file='../../css/fonts.css' %>
            <%@include file='../../css/media.css' %>
            <%@include file='../../css/main.css' %>
        </style>
        <link href="../../css/fonts.css" type="text/css">
    <meta charset="utf-8">
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">   
    </head>
    <body>
        <center>  
        <div style="padding-left: 5%; padding-right: 5%;"> 
            
            <div class="navbar navbar-inverse">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
                        <span class="sr-only">Открыть навигацию</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/restaurant/main">Elite</a>
                </div>
                <div class="collapse navbar-collapse" id="responsive-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="/restaurant/scheme">Схема ресторана</a></li>
                        <li><a href="/restaurant/employees">Наш персонал</a></li>
                        <li><a href="/restaurant/contacts">Контакты</a></li>
                    </ul>
                </div>
            </div>
            <h1><b>Ресторан <i>Элитный</i></b></h1><br><br>
            
            <h1 class="style-color">Меню:</h1>
                
            <form action="" method="post">
                Введите название блюда:<input type="text" name="name"> <input type="submit" value="Поиск"><br>
            </form>
                    <table class="style-color" border="1px" width="450px">
                    <tr>
                        <th>Название</th>
                        <th>Вес</th>
                        <th>Цена</th>
                    </tr>
                    <c:forEach items="${allDishes}" var="dish">
                        <tr>
                            <td><a href="/restaurant/main/dishdetails/${dish.getId()}">${dish.getName()}</a></td>
                            <td>${dish.getWeight()}</td>
                            <td>${dish.getCostOf()}</td>
                        </tr>
                    </c:forEach>
                    </table>
            <br><br>
                <b>Адрес: <i>Улица Пушкина дом калатушкина</i></b><br>
                <b>Контактный телефон: <i>+38(093)-5018-566</i></b><br>
                <b>Почта: <i>olexiyyefimenko@gmail.com</i></b>
        </div>
        </center>   
    </body>
</html>    