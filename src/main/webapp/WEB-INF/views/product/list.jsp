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
            height: 100vh;
            gap: 10px;
        }
        .header {
            margin-bottom: 20px;
        }
        /*.side {*/
        /*    grid-area: side;*/
        /*    margin-bottom: 20px;*/
        /*    padding: 25px;*/
        /*}*/
        /*.side ul {*/
        /*    list-style-type: none;*/
        /*    padding: 0;*/
        /*    margin: 0;*/
        /*    border: 1px solid #dee2e6;*/
        /*    border-radius: 5px;*/
        /*    overflow: hidden;*/
        /*}*/
        /*.side ul li {*/
        /*    border-bottom: 1px solid #dee2e6;*/
        /*}*/
        /*.side ul li:last-child {*/
        /*    border-bottom: none;*/
        /*}*/
        /*.side ul li a {*/
        /*    display: block;*/
        /*    padding: 15px;*/
        /*    text-decoration: none;*/
        /*    color: #333;*/
        /*    font-weight: bold;*/
        /*    transition: background 0.3s;*/
        /*}*/
        /*.side ul li a:hover {*/
        /*    background-color: #f8f9fa;*/
        /*    color: #007bff;*/
        /*}*/
        /*.side ul li a.active {*/
        /*    background-color: #007bff;*/
        /*    color: #fff;*/
        /*}*/
        .main {

        }
        .card{
            margin-top : 25px;
            padding: 15px;
            display: flex; /* 카드를 가로로 정렬 */
            gap: 20px; /* 카드 간 간격 설정 */
            flex-wrap: wrap; /* 공간이 부족할 때 줄바꿈 */
        }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <%@include file="/main/header.jsp" %>
        <div class="search">
            <form class="d-flex" role="search" action="/product/list" method="GET">
                <select class="form-select" name="searchCategory" aria-label="Default select example">
                    <option value="productName" selected>상품명</option>
                    <option value="sellerId">판매자</option>
                </select>
                <input class="form-control me-2" type="search"  name="searchValue"  placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
        <div class="card-body">
            <c:if test="${not empty pList}">
                <c:forEach var="dto" items="${pList}" varStatus="loop">
                    <a href="/product/view?productId=${dto.productId}">
                        <div class="card" style="width: 18rem; margin-bottom: 20px;">
                            <c:choose>
                                <c:when test="${not empty dto.imagePaths}">
                                    <img src="${dto.imagePaths[0]}" class="card-img-top" alt="${dto.productName}">
                                </c:when>
                                <c:otherwise>
                                    <img src="/resources/images/default.png" class="card-img-top" alt="기본 이미지">
                                </c:otherwise>
                            </c:choose>
                            <div class="card-body">
                                <h5 class="card-title">${dto.productName}</h5>
                                <p class="card-text">가격: ${dto.price}원</p>
                                <p class="card-text">조회수: ${dto.viewCount}</p>
                                <p class="card-text">등록일: ${dto.regDate}</p>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </c:if>

<%--        <table class="table">--%>
<%--            <thead class="thead-dark">--%>
<%--            <tr>--%>
<%--                <th scope="col">상품명</th>--%>
<%--                <th scope="col">가격</th>--%>
<%--                <th scope="col">조회수</th>--%>
<%--                <th scope="col">등록일</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <c:if test="${not empty pList}">--%>
<%--                <c:forEach var="dto" items="${pList}" varStatus="loop">--%>
<%--                    <tr>--%>
<%--                        <td><a href="/bbs/view.do?idx=${dto.productId}">${dto.productName}</a></td>--%>
<%--                        <td>${dto.price}</td>--%>
<%--                        <td>${dto.viewCount}</td>--%>
<%--                        <td>${dto.regDate}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </c:if>--%>
<%--            </tbody>--%>
<%--        </table>--%>
    <%@ include file="../common/paging.jsp"%>
    <div class="side">
        <br>
<%--        <ul>--%>
<%--            <li><a href="../product/list?&subjectCode=KO" class="${(param.subjectCode eq 'KO') ? 'active' : ''}">국어</a></li>--%>
<%--            <li><a href="../product/list?&subjectCode=MA" class="${(param.subjectCode eq 'MA') ? 'active' : ''}">수학</a></li>--%>
<%--            <li><a href="../product/list?&subjectCode=EN" class="${(param.subjectCode eq 'EN') ? 'active' : ''}">영어</a></li>--%>
<%--        </ul>--%>
    </div>
    <div class="main">
        <!-- 페이징 영역 -->
<%--        <%@ include file="../common/paging.jsp"%>--%>
        <!-- //페이징 영역 -->
    </div>
     <%@include file="/main/footer.jsp" %>

</body>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>