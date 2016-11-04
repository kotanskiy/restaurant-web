<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html; charset=UTF-8" language = "java" %>
<html>
    <head>
        <title>Добавить ингредиент на склад</title>
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
                
                <h3>Список ингредиентов, которых нет на складе</h3><br>
                <table class="style-color" border="1px">
                    <tr>
                        <th>Название ингредиента</th>
                    </tr>
                    <c:forEach items="${ingredients}" var="ingredient">
                        <tr>
                            <td><b>${ingredient.getName()}</b> <form action="" method="post"> <input name="id" type="hidden" value="${ingredient.getId()}"> Кол-во:<input type="number" name="count" value="${count}"><input type="submit" value="Отправить"></form> </td>
                        </tr>
                    </c:forEach>
                </table>
                
                
                
            </div>
        </center>  
    </body>
</html>    