<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    String locationDataJson = (String) request.getAttribute("locationData");
    System.out.println("Location Data JSON: " + locationDataJson); // 데이터 확인
%>


<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        #title {
            font-size: 25px;
            font-family: "맑은 고딕";
            font-weight: bold;
        }
        #hyper_link1, #hyper_link2, #hyper_link3 {
            font-size: 12px;
            font-family: "맑은 고딕";
        }
        #label1, #label2, #label3, #label4 {
            font-size: 12px;
            font-family: "맑은 고딕";
        }
        #textbox1, #textbox2 {
            width: 130px;
            height: 13px;
        }
        #button1, #button2 {
            font-size: 12px;
            width: 125px;
            height: 20px;
        }
        #data-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        #data-table th, #data-table td {
            font-size: 12px;
            border: 1px solid white;
            border-bottom: 1px solid grey;
            border-left: 1px solid grey;
            border-right: 1px solid grey;
            padding: 8px;
            text-align: center;
        }
        #data-table th {
            background-color: #04AA6D;
            color: white;
            font-size: 12px;
        }
        tr:nth-child(even) {background-color: #f2f2f2;}
    </style>
</head>
<body>

<script src="${pageContext.request.contextPath}/resource/js/index.js" defer></script>
<script>
    var jsonData = '<%= locationDataJson %>';
    var isdataset = false; // 전역 변수
        window.onload = function() {
            if(!isdataset){
                /*resetdata();*/
                isdataset = true;
            }

            if (jsonData) {
                try {
                    var data = JSON.parse(jsonData);
                    createTable(data);
                } catch (error) {
                    console.error('Error parsing jsonData:', error);
                }
            }
        }
</script>

<h1 id="title">와이파이 정보 구하기</h1>

<div>
    <a id="hyper_link1" href="/mainPage">홈</a> <%--구현 완--%>
    <label id="label1"> | </label>
    <a id="hyper_link2" href="location_history.jsp">위치 히스토리 목록</a>
    <label id="label2"> | </label>
    <a id="hyper_link3" href="${pageContext.request.contextPath}/DataLoad">Open API 와이파이 정보 가져오기</a> <%--구현 완--%>
</div>
<br>
<div>
    <label id="label3">LAT:</label>
    <input type="text" id="textbox1" placeholder="0.0" required>
    <label id="label4">, LNT:</label>
    <input type="text" id="textbox2" placeholder="0.0" required>
    <button id="button1" onclick="getLocation()">내 위치 가져오기</button> <%--구현 완--%>
    <button id="button2" onclick="fetchData()">근처 WIFI 정보 보기</button>
</div>
<div id="table-container"></div>


</body>
</html>