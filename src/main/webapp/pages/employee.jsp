<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html; charset=UTF-8" language = "java" %>
<html>
    <head>
        <title>Персонал</title>
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
                
                <h1 class="style-color">Список сотрудников:</h1>
                
                    <table class="style-color" border="1px">
                    <tr>
                        <th>Имя и фамилия</th>
                        <th>Дата рождения</th>
                        <th>Позиция</th>
                        <th>Зарплата</th>
                        <th>Номер телефона</th>
                    </tr>
                    <c:forEach items="${allEmployees}" var="employee">
                        <tr>
                            <td>${employee.getName()} ${employee.getSurname()}</td>
                            <td>${employee.getDataBirth()}</td>
                            <td>${employee.getPosition()}</td>
                            <td>${employee.getSalary()}</td>
                            <td>${employee.getPhone()}<a href="/restaurant/editemployee/${employee.getId()}">Редактировать</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <h3><a href="/restaurant/addemployee/${employee.getId()}">Добавить нового сотрудника</a></h3>
            </div>
        </center>  
        
            
    </body>
</html>    