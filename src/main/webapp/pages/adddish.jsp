<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html; charset=UTF-8" language = "java" %>
<html>
    <head>
        <title>Добавить или редактировать блюдо</title>
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
                
                <h3>Редактировать Блюдо</h3><br>
                <form action="" method="post">
                    Введите название блюда: <input type="text" name="name" value="${dish.getName()}"><br>
                    Введите категорию блюда: <input type="text" name="category" value="${dish.getCategory()}"><br>
                    Введите цену блюда: <input type="text" name="costOf" value="${dish.getCostOf()}"><br>
                    Введите вес блюда: <input type="text" name="weight" value="${dish.getWeight()}"><br>
                    <input type="submit" value="Отправить">
                </form>
                
                
                
            </div>
        </center>  
    </body>
</html>    