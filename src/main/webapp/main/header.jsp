<%--
  Created by IntelliJ IDEA.
  User: full5-4
  Date: 2024-11-11
  Time: 오후 5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <style>
    /* Navbar */
    .navbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 20px;
      background-color: #fff;
      border-bottom: 1px solid #ddd;
    }

    .logo {
      display: flex;
      align-items: center;
    }

    .logo img {
      width: 40px;
      height: auto;
      margin-right: 10px;
    }

    .logo span {
      font-weight: bold;
      font-size: 1.2rem;
      color: #333;
    }

    .navbar nav ul {
      list-style: none;
      display: flex;
      gap: 20px;
    }

    .navbar nav ul li a {
      text-decoration: none;
      color: #333;
      font-weight: bold;
      font-size: 23px;
    }

    .auth-links {
      display: flex;
      gap: 10px;
    }

    .auth-links a {
      text-decoration: none;
      color: #333;
    }
  </style>
</head>
<body>

<header>
  <div class="navbar">
    <div class="logo">
      <img src="/images/logo.png" alt="IT Shop Logo">
      <span style="font-size: 13px;">선생님들을 위한 중고거래 사이트 </span><span style="margin-left: 5px;">당신마켓</span>
    </div>
    <nav>
      <ul>
        <li><a href="#">상품</a></li>
        <li><a href="#">내상품</a></li>
        <li><a href="#">셀파톡</a></li>
        <li><a href="#">공지사항</a></li>
      </ul>
    </nav>
    <div class="auth-links">
      <!--로그인 전-->
      <a href="#">로그인</a> | <a href="#">회원가입</a>
      <!--로그인 시-->
      <a href="#">마이페이지</a>| <a href="#">로그아웃</a>
    </div>
  </div>
</header>

</body>
</html>
