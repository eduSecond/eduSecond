<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
            background-color: whitesmoke;
            padding: 15px;
            color: black;
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
            margin: 2%;
            margin-left: 10%;
            padding: 20px;
            flex-grow: 1;
            border: 2px solid lightgrey;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: white;
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
            <li>판매자명 : ${dto.sellerId}</li>
            <li>상품명 : ${dto.productName}</li>
            <li>가격 : ${dto.price} 원</li>
        </ul>
    </div>
    <div class="content col-7">
        <h2></h2>
        <br>
        <div class="mb-3">
            <label for="name" class="form-label">받는 사람</label>
            <input type="text" class="form-control" id="name">
        </div>
        <div class="mb-3">
            <label for="tel" class="form-label">전화번호</label>
            <input type="text" class="form-control" id="tel">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="name@example.com">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">주소</label>
            <input type="text" class="form-control" id="addr">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">우편번호</label>
            <input type="text" class="form-control" id="zipcode">
        </div>
        <div class="mb-3">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
                <label class="form-check-label" for="inlineCheckbox1">신용카드</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2">
                <label class="form-check-label" for="inlineCheckbox2">무통장입금</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3" disabled>
                <label class="form-check-label" for="inlineCheckbox3">간편결제</label>
            </div>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">결제회사</label> <!-- 신용카드 체크하면 보여주는게 좋을 듯 -->
            <input type="text" class="form-control" id="paymentCompany">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">결제번호</label>
            <input type="text" class="form-control" id="paymentNumber">
        </div>
    </div>
</div>
<%@include file="/main/footer.jsp"%>
</body>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>