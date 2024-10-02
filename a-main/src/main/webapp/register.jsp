<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
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

        #register {
            color: black;
            font-size: 25px;
            font-weight: bold;
            text-align: center;
        }

        .register-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .register-container label {
            display: block;
            margin-top: 10px;
        }
        .register-container input[type="text"],
        .register-container input[type="email"],
        .register-container input[type="password"] {
            width: 95%;
            height: 20pt;
            padding-right: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .register-container input[type="submit"] {
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
        .register-container input[type="submit"]:hover {
            background-color: #45a049;
        }
/*        #id {
            width: 95%;
            padding-right: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }*/
    </style>
</head>
<body>
<div class="register-container">
    <p id="register">회원가입</p>
    <input type="button" id="btn_info" value="&lt;" onclick="redirectToLogin()">
    <form action="register" method="post">
        <label for="name">이름</label>
        <input type="text" id="name" name="name" required>
        <label for="id">ID (이메일 형식)</label>
        <input type="email" id="id" name="id" required>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
        <label for="number">전화번호</label>
        <input type="text" id="number" name="number" required>
        <input type="submit" value="회원가입 하기">
    </form>
</div>
</body>
</html>
