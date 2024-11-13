<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>툴</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f9f9f9;
            color: #333;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .privacy-policy {
            max-width: 1500px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        .mainpage {
            display: flex;
            flex-grow: 1;
            min-height: calc(100vh); /* header와 footer를 제외한 높이 설정 */
        }

        .sidebar {
            background-color: #444444;
            padding: 15px;
            color: #fff;
            height: 100%;
        }

        .sidebar ul {
            list-style: none;
            padding: 0;
        }

        .sidebar ul li {
            margin-bottom: 10px;
        }

        .sidebar ul li a {
            color: #fff;
            text-decoration: none;
        }

        .sidebar ul li a:hover {
            text-decoration: underline;
        }

        .content {
            padding: 20px;
            flex-grow: 1;
        }

        .action-buttons {
            margin-top: 20px;
            text-align: right;
        }

        .footer {
            background-color: #f8f8f8;
            padding: 10px;
            text-align: center;
            border-top: 1px solid #ddd;
            width: 100%;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="/main/header.jsp"%>

<div class="mainpage">
    <div class="sidebar col-2">
        <ul>
            <li><a href="/es/mypage/myInfo">내 정보</a></li>
            <li><a href="/es/mypage/wishList">찜 목록</a></li>
            <li><a href="/es/mypage/orderList">거래 내역</a></li>
            <li><a href="/es/product/productList">내 상품</a></li>
            <li><a href="/es/mypage/messageList">셀파톡</a></li>
        </ul>
    </div>

    <div class="content col-10">
        <div class="d-flex justify-content-between" id="additional" >
            <div class="asdr">
                <h2>내가 등록한 상품</h2>
            </div>
            <div class="asdr">
                <h2>내가 찜한 상품</h2>
                <p>d아앙ㄱ</p>
            </div>
            <div class="asdr">
                <h2>내가 받은 리뷰</h2>
                <p>d아앙ㄱ</p>
            </div>
        </div>
        <h2>내 정보 수정</h2>
        <div>
            <form method="post" action="/es/mypage/modifyOk">
                <table class="table table-borderless">
                    <tr>
                        <th>아이디</th>
                        <td>${member.userId}</td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td>${member.userName}</td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="email" value="${member.userEmail}" name="userEmail"></td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td><input type="text" value="${member.userPhone}" name="userPhone"></td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td><input type="text" value="${member.userAddress}" name="userAddress"></td>
                    </tr>
                    <tr>
                        <th>생일</th>
                        <td><input type="date" value="${member.userBirth}" name="userBirth"></td>
                    </tr>
                    <tr>
                        <th>가입일</th>
                        <td>${member.regDate}</td>
                    </tr>
                </table>
                <button>등록</button>
                <button type="button" onclick="location.href='/es/mypage/myInfo'">취소</button>
            </form>

        </div>
    </div>
</div>

<%@include file="/main/footer.jsp"%>
</body>
</html>