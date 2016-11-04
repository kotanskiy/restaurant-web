<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType = "text/html; charset=UTF-8" language = "java" %>
<html>
    <head>
        <title>Добавить или редактировать сотрудника</title>
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
                
                <h3>Редактировать сотрудника</h3><br>
                <form action="" method="post">
                    Введите имя: <input type="text" name="name" value="${employee.getName()}"><br>
                    Введите фамилию: <input type="text" name="surname" value="${employee.getSurname()}"><br>
                    Введите дату рождения: <input type="text" name="date_birth" value="${employee.getDataBirth()}"><br>
                    Введите должность: <input type="text" name="position" value="${employee.getPosition()}"><br>
                    Введите зарплату: <input type="text" name="salary" value="${employee.getSalary()}"><br>
                    Введите номер телефона: <input type="text" name="phone" value="${employee.getPhone()}"><br>
                    <input type="submit" value="Отправить">
                </form>
                
                
                
            </div>
        </center>  
    </body>
</html>    