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
    <h2>거래 내역</h2>
    <span><a href="/es/mypage/orderList">내가 구매한 상품</a></span> &nbsp; &nbsp; <span><a href="/es/mypage/orderList_1">내가 판매한 상품</a></span>
    <form class="d-flex" action="/es/mypage/orderList" method="get">
      <input class="form-control me-2" type="text" placeholder="Search" aria-label="Search" name="searchValue">
      <input type="hidden" value="productName" name="searchCategory">
      <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
    <div>
      <table class="table">
        <thead>
        <tr>
          <th scope="col">상품 사진</th>
          <th scope="col">상품명</th>
          <th scope="col">판매자 아이디</th>
          <th scope="col">단위가격/개수</th>
          <th scope="col">총 가격</th>
          <th scope="col">배송 상태</th>
          <th scope="col">받는분/배송주소</th>
          <th scope="col">결제번호</th>
          <th scope="col">결제방식/결제회사</th>
          <th scope="col">결제일</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty orderList}">
          <c:forEach var="dto" items="${orderList}" >
            <tr>
              <td>
              <c:if test="${not empty dto.imagePath}">
                <img src="${dto.imagePath}"
                     alt="상품 썸네일"
                     style="width: 50px; height: 50px; object-fit: cover;">
              </c:if>
              </td>
              <td><a href="/product/view?productId=${dto.productId}">${dto.productName}</a></td>
              <td>${dto.sellerId}</td>
              <td>${dto.unitPrice}원/${dto.orderQuantity}개</td>
              <td>${dto.totalPrice}원</td>
              <td>
                  ${dto.deliveryStatus}
                  <c:if test="${dto.deliveryStatus eq '배송중'}">
                    <button onclick="location.href='/es/payment/confirmPurchase?productId=${dto.productId}&pageNo=${param.pageNo}'">구매확정</button>
                  </c:if>
              </td>
              <td>${dto.recipientName}/${dto.shippingAddress}</td>
              <td>${dto.paymentNumber}</td>
              <td>${dto.paymentMethod}/${dto.paymentCompany}</td>
              <td>${dto.regDate}</td>
            </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty orderList}">
          <tr>목록이 없습니다</tr>
        </c:if>
        </tbody>
      </table>
      <%@ include file="../common/paging.jsp"%>

    </div>
  </div>
</div>

<%@include file="../main/footer.jsp"%>
</body>
</html>