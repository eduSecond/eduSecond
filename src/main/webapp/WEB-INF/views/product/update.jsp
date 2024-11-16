<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>상품 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .image-preview {
            position: relative;
            width: 150px;
            height: 150px;
            margin: 10px;
            display: inline-block;
        }
        .image-preview img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        .delete-button {
            position: absolute;
            top: 5px;
            right: 5px;
            background: rgba(255, 0, 0, 0.7);
            color: white;
            border: none;
            border-radius: 50%;
            width: 25px;
            height: 25px;
            cursor: pointer;
        }
        .main-badge {
            position: absolute;
            top: 5px;
            left: 5px;
            background: rgba(0, 0, 255, 0.7);
            color: white;
            padding: 2px 8px;
            border-radius: 10px;
            font-size: 12px;
        }
    </style>
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="container mt-5">
    <h2>상품 수정</h2>
    <form action="/product/updateOk" method="post" enctype="multipart/form-data">
        <input type="hidden" name="productId" value="${dto.productId}">
        
        <div class="mb-3">
            <label for="productName" class="form-label">상품명</label>
            <input type="text" class="form-control" id="productName" name="productName" value="${dto.productName}">
        </div>
        
        <div class="mb-3">
            <label for="productDesc" class="form-label">상품 설명</label>
            <textarea class="form-control" id="productDesc" name="productDesc" rows="5">${dto.productDesc}</textarea>
        </div>
        
        <div class="mb-3">
            <label for="price" class="form-label">가격</label>
            <input type="number" class="form-control" id="price" name="price" value="${dto.price}">
        </div>
        
        <div class="mb-3">
            <label for="quantity" class="form-label">수량</label>
            <input type="number" class="form-control" id="quantity" name="quantity" value="${dto.quantity}">
        </div>
        
        <div class="mb-3">
            <label for="quality" class="form-label">상품 상태</label>
            <select class="form-control" id="quality" name="quality">
                <option value="NEW" ${dto.quality == 'NEW' ? 'selected' : ''}>새상품</option>
                <option value="LIKE_NEW" ${dto.quality == 'LIKE_NEW' ? 'selected' : ''}>거의 새것</option>
                <option value="GOOD" ${dto.quality == 'GOOD' ? 'selected' : ''}>양호</option>
                <option value="FAIR" ${dto.quality == 'FAIR' ? 'selected' : ''}>사용감 있음</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="productStatus" class="form-label">판매 상태</label>
            <select class="form-control" id="productStatus" name="productStatus">
                <option value="AVAILABLE" ${dto.productStatus == 'AVAILABLE' ? 'selected' : ''}>판매중</option>
                <option value="RESERVED" ${dto.productStatus == 'RESERVED' ? 'selected' : ''}>예약중</option>
                <option value="SOLD" ${dto.productStatus == 'SOLD' ? 'selected' : ''}>판매완료</option>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">현재 이미지</label>
            <div class="d-flex flex-wrap">
                <c:forEach items="${images}" var="image">
                    <div class="image-preview">
                        <img src="${image.imagePath}" alt="상품 이미지">
                        <button type="button" class="delete-button" onclick="toggleImageDelete(${image.imageId})">×</button>
                        <input type="checkbox" name="deleteImageIds" value="${image.imageId}" style="display: none;">
                        <c:if test="${image.isMain == 'Y'}">
                            <span class="main-badge">대표</span>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="mb-3">
            <label for="files" class="form-label">새 이미지 추가</label>
            <input type="file" class="form-control" id="files" name="files" multiple>
            <small class="text-muted">최대 4장까지 가능합니다.</small>
        </div>

        <button type="submit" class="btn btn-primary">수정하기</button>
        <a href="/product/view?productId=${dto.productId}" class="btn btn-secondary">취소</a>
    </form>
</div>
<%@include file="../main/footer.jsp"%>

<script>
function toggleImageDelete(imageId) {
    const checkbox = document.querySelector(`input[value="${imageId}"]`);
    const button = checkbox.previousElementSibling;
    checkbox.checked = !checkbox.checked;
    if (checkbox.checked) {
        button.style.backgroundColor = 'rgba(0, 0, 0, 0.7)';
    } else {
        button.style.backgroundColor = 'rgba(255, 0, 0, 0.7)';
    }
}
</script>
</body>
</html>
