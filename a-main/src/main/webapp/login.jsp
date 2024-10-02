<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="dto.User"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <script src="/resource/js/index.js" defer></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            height: 20pt;
            padding-right: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }
        .login-container input[type="submit"]:hover { <%-- 사용자가 버튼 위로 마우스를 가져갔을 때의 효과 --%>
            background-color: #45a049;
        }
        .if-container {
            margin-top: 5px;
            display: flex;
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
        }
        .if-container p {
            color: black;
            font-size: 10px;
            margin-right: 10px;
        }
        .if-container a {
            color: cornflowerblue;
            font-size: 10px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="login" method="post">
        <label for="id">ID (Email):</label>
        <input type="text" id="id" name="id" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <input type="submit" value="로그인"> <%-- 데이터를 서버로 전송 --%>
    </form>
    <div class ="if-container">
        <p>회원이 아니라면?</p>
        <a href="http://localhost:8080/register.jsp">회원가입</a>
    </div>
</div>
</body>
</html>