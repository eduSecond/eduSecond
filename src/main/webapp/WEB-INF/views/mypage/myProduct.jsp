<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@ include file="../main/header.jsp"%>
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
        <h2>판매중 상품</h2>
        <form class="d-flex" role="search" action="/es/mypage/myProduct" method="GET">
            <select name="searchType" class="form-select" style="width: 120px;">
                <option value="productName" ${searchType == 'productName' ? 'selected' : ''}>상품명</option>
                <option value="sellerId" ${searchType == 'sellerId' ? 'selected' : ''}>판매자</option>
            </select>
            <input class="form-control me-2" type="search" name="searchValue" value="${searchValue}" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">상품명</th>
                <th scope="col">가격</th>
                <th scope="col">상태</th>
            </tr>
            </thead>
            <tbody>
                    <c:if test="${not empty availableList}">
                        <c:forEach var="dto" items="${availableList}" >
                            <tr>
                                <td>
                                    <c:if test="${not empty dto.thumbnail}">
                                        <img src="/resources/${dto.thumbnail.imagePath}"
                                             alt="상품 썸네일"
                                             style="width: 50px; height: 50px; object-fit: cover;">
                                    </c:if>
                                </td>
                                <td>${dto.productName}</td>
                                <td>${dto.sellerId}</td>
                                <td>${dto.price}원</td>
                                <td>${dto.productStatus}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
            </tbody>
        </table>
        <%@ include file="../common/paging.jsp"%>
        <br>
        <br>
        <h2>판매 완료 상품</h2>
        <form class="d-flex" role="search" action="/es/mypage/myProduct" method="GET">
            <select name="searchType" class="form-select" style="width: 120px;">
                <option value="productName" ${searchType == 'productName' ? 'selected' : ''}>상품명</option>
                <option value="sellerId" ${searchType == 'sellerId' ? 'selected' : ''}>판매자</option>
            </select>
            <input class="form-control me-2" type="search" name="searchValue" value="${searchValue}" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">상품명</th>
                <th scope="col">가격</th>
                <th scope="col">상태</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty soldOutList}">
                <c:forEach var="dto" items="${soldOutList}" >
                    <tr>
                        <td>
                        <c:if test="${not empty dto.thumbnail}">
                            <img src="/resources/${dto.thumbnail.imagePath}"
                                 alt="상품 썸네일"
                                 style="width: 50px; height: 50px; object-fit: cover;">
                        </c:if>
                        </td>
                        <td>${dto.productName}</td>
                        <td>${dto.sellerId}</td>
                        <td>${dto.price}원</td>
                        <td>${dto.productStatus}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <!-- 이전 페이지 블록 버튼 -->
                <c:if test="${soldOutPaging.prevBlock}">
                    <li class="page-item">
                        <a class="page-link" href="?pageNo=${soldOutPaging.startBlockPage - 1}&searchCategory=${param.searchCategory}&searchValue=${param.searchValue}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <!-- 현재 페이지 블록 버튼들 -->
                <c:forEach begin="${soldOutPaging.startBlockPage}" end="${soldOutPaging.endBlockPage}" var="i">
                    <li class="page-item ${i == param.pageNo ? 'active' : ''}">
                        <a class="page-link" href="?pageNo=${i}&searchCategory=${param.searchCategory}&searchValue=${param.searchValue}">${i}</a>
                    </li>
                </c:forEach>

                <!-- 다음 페이지 블록 버튼 -->
                <c:if test="${soldOutPaging.nextBlock}">
                    <li class="page-item">
                        <a class="page-link" href="?pageNo=${soldOutPaging.endBlockPage + 1}&searchCategory=${param.searchCategory}&searchValue=${param.searchValue}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
<%@include file="../main/footer.jsp"%>
</body>
</html>