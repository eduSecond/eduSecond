<%--
  Created by IntelliJ IDEA.
  User: full5-5
  Date: 2024-11-08
  Time: 오후 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <!-- 상단 네비게이션-->
    <div class="row">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Link</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Dropdown
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Action</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                    <form class="d-flex" role="search" action="/es/product/list" method="GET">
                        <select class="form-select" name="searchCategory" aria-label="Default select example">
                            <option value="productName" selected>상품명</option>
                            <option value="productId">판매자</option>
                        </select>
                        <input class="form-control me-2" type="search"  name="searchValue"  placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
    </div>
    <!-- //상단 네비게이션-->
    <!-- 컨텐츠 영역-->
    <div class="row content">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    게시판 - 목록
                </div>
                <div class="card-body">
                    <!-- 검색영역 -->
                    <!-- //검색영역 -->
                    <!-- 리스트 영역 -->
<%--                    <p>${pList.size()}</p>--%>

<%--                    <c:if test="${not empty pList}">--%>
<%--                        <c:forEach var="dto" items="${pList}" varStatus="loop">--%>
<%--                            <div class="card" style="width: 18rem; margin-bottom: 20px;">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${not empty dto.imagePaths}">--%>
<%--                                        <img src="${dto.imagePaths[0]}" class="card-img-top" alt="${dto.productName}">--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>--%>
<%--                                        <img src="/resources/images/default.png" class="card-img-top" alt="기본 이미지">--%>
<%--                                    </c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                                <div class="card-body">--%>
<%--                                    <h5 class="card-title">${dto.productName}</h5>--%>
<%--                                    <p class="card-text">가격: ${dto.price}원</p>--%>
<%--                                    <p class="card-text">조회수: ${dto.viewCount}</p>--%>
<%--                                    <p class="card-text">등록일: ${dto.regDate}</p>--%>
<%--                                    <a href="/bbs/view.do?idx=${dto.productId}" class="btn btn-primary">상세 보기</a>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </c:forEach>--%>
<%--                    </c:if>--%>

                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">상품명</th>
                            <th scope="col">가격</th>
                            <th scope="col">조회수</th>
                            <th scope="col">등록일</th>
                        </tr>
                        </thead>
                        <tbody>
                        <p>${pList.size()}</p>
                        <c:if test="${not empty pList}">
                            <c:forEach var="dto" items="${pList}" varStatus="loop">
                                <tr>
                                    <th scope="row">${fn:length(pList) - loop.index}</th>
                                    <td><a href="/bbs/view.do?idx=${dto.productId}">${dto.productName}</a></td>
                                    <td>${dto.price}</td>
                                    <td>${dto.viewCount}</td>
                                    <td>${dto.regDate}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                    <!-- //리스트 영역 -->
                    <!-- 페이징 영역 -->
                    <%@ include file="../common/paging.jsp"%>
                    <!-- //페이징 영역 -->
                    <div class="form-group row justify-content-center mt-3">
                        <div class="col-sm-3">
                            <button type="submit" class="btn btn-primary" onclick="javascript:location.href='/bbs/regist.do'">등록</button>
                            <button type="button" class="btn btn-success" onclick="javascript:location.href='/bbs/list.do'">목록으로</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //컨텐츠 영역-->
    <div class="row">

    </div>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
