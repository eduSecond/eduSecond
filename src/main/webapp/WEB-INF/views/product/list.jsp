<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            margin: 0;
            padding: 0;
        }
        .container {
            display: grid;
            grid-template-areas:
                "header header"
                "side main"
                "footer footer";
            grid-template-columns: 1fr 3fr;
            grid-template-rows: minmax(172px, 1fr) 8fr minmax(172px, 1fr);
            height: 100vh;
            gap: 10px;
        }
        .header {
            grid-area: header;
            margin-bottom: 20px;
        }
        .side {
            grid-area: side;
            margin-bottom: 20px;
            padding: 25px;
        }
        .side ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            border: 1px solid #dee2e6;
            border-radius: 5px;
            overflow: hidden;
        }
        .side ul li {
            border-bottom: 1px solid #dee2e6;
        }
        .side ul li:last-child {
            border-bottom: none;
        }
        .side ul li a {
            display: block;
            padding: 15px;
            text-decoration: none;
            color: #333;
            font-weight: bold;
            transition: background 0.3s;
        }
        .side ul li a:hover {
            background-color: #f8f9fa;
            color: #007bff;
        }
        .side ul li a.active {
            background-color: #007bff;
            color: #fff;
        }
        .main {
            grid-area: main;
            margin-top : 25px;
            padding: 15px;
            display: flex; /* 카드를 가로로 정렬 */
            gap: 20px; /* 카드 간 간격 설정 */
            flex-wrap: wrap; /* 공간이 부족할 때 줄바꿈 */
        }
        .footer {
            grid-area: footer;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <%@include file="/main/header.jsp" %>
        <form class="d-flex" role="search" action="/es/product/list" method="GET">
            <select class="form-select" name="searchCategory" aria-label="Default select example">
                <option value="productName" selected>상품명</option>
                <option value="sellerId">판매자</option>
            </select>
            <input class="form-control me-2" type="search"  name="searchValue"  placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
  </div>
    <div class="side">
        <br>
        <ul>
            <li><a href="../teacher/list.do?&subjectCode=KO" class="${(param.subjectCode eq 'KO') ? 'active' : ''}">국어</a></li>
            <li><a href="../teacher/list.do?&subjectCode=MA" class="${(param.subjectCode eq 'MA') ? 'active' : ''}">수학</a></li>
            <li><a href="../teacher/list.do?&subjectCode=EN" class="${(param.subjectCode eq 'EN') ? 'active' : ''}">영어</a></li>
            <li><a href="../teacher/list.do?&subjectCode=SO" class="${(param.subjectCode eq 'SO') ? 'active' : ''}">사회</a></li>
            <li><a href="../teacher/list.do?&subjectCode=SC" class="${(param.subjectCode eq 'SC') ? 'active' : ''}">과학</a></li>
        </ul>
    </div>
    <div class="main">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">썸네일</th>
                <th scope="col">상품명</th>
                <th scope="col">가격</th>
                <th scope="col">조회수</th>
                <th scope="col">등록일</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty products}">
                <c:forEach var="product" items="${products}" varStatus="loop">
                    <tr>
                        <td>
                            <c:if test="${not empty product.thumbnail}">
                                <img src="${product.thumbnail.imagePath}" 
                                     alt="상품 썸네일" 
                                     style="width: 50px; height: 50px; object-fit: cover;">
                            </c:if>
                        </td>
                        <td><a href="/es/product/view?productId=${product.productId}">${product.productName}</a></td>
                        <td>${product.price}</td>
                        <td>${product.viewCount}</td>
                        <td>${product.regDate}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <!-- 페이징 영역 -->
        <%@ include file="../common/paging.jsp"%>
        <!-- //페이징 영역 -->
    </div>
</div>
</body>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>