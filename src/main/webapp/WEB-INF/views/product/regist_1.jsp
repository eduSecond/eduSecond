<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jerry
  Date: 2024-11-11
  Time: 오후 7:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품 등록</title>
</head>
<body>
<form action="/product/registOk" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="productName">상품 이름</label>
        <input type="text" class="form-control" id="productName" name="productName">
    </div>
    <div class="input-group">
        <span class="input-group-text">상품 설명</span>
        <textarea class="form-control" aria-label="With textarea" rows="10" name="productDesc"></textarea>
    </div>
    <div>
        <label for="price">가격</label>
        <input type="number" id="price" name="price">
    </div>
    <div>
        <label for="quantity">수량</label>
        <input type="number" id="quantity" name="quantity">
    </div>
    <div>
        <label for="quality">상품 상태</label>
        <select id="quality" name="quality">
            <option value="NEW">new</option>
            <option value="LIKE_NEW">likenew</option>
            <option value="GOOD">good</option>
            <option value="FAIR">fair</option>
        </select>
    </div>
    <div>
        <input type="file" name="files" multiple>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    <c:if test="${not empty errors}">
        <div class="alert alert-danger">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error.defaultMessage}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
</form>
</body>
</html>
