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
    }

    .privacy-policy {
      max-width: 1500px;
      margin: 50px auto;
      padding: 20px;
      background-color: #fff;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
    }

    .carousel-item{
      border: 1px solid red;
      align-content: center;
    }
  </style>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@ include file="/main/header.jsp"%>
<section class="privacy-policy">
  <div id="carouselExampleIndicators" class="carousel slide">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="/resources/images/product/Ko1.jpg" class="d-block w-100" alt="..." width="300px" height="250px">
      </div>
      <div class="carousel-item">
        <img src="/resources/images/product/Ko2.jpg" class="d-block w-100" alt="..." width="300px" height="250px">
      </div>
      <div class="carousel-item">
        <img src="/resources/images/product/Ko3.jpg" class="d-block w-100" alt="..." width="300px" height="250px">
      </div>
      <div class="carousel-item">
        <img src="/resources/images/product/Ko4.jpg" class="d-block w-100" alt="..." width="300px" height="250px">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
  <div class="content">
    <ul>
      <li>${dto.productId}</li>
      <li>${dto.productName}</li>
      <li>${dto.price}</li>
      <li>${dto.quantity}</li>
      <li>${dto.quality}</li>
    </ul>
  </div>
  <div class="row">
    <div class="col-sm-3 mb-3 mb-sm-0">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Special title treatment</h5>
          <p class="card-text">구매하려면 버튼 눌러라</p>
          <a href="#" class="btn btn-primary">1:1 문의하기</a>
          <a href="/es/payment/view?productId=${dto.productId}" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#purchaseModal">구매하기</a>
        </div>
      </div>
    </div>
  </div>
  <!-- 모달 -->
  <div class="modal fade" id="purchaseModal" tabindex="-1" aria-labelledby="purchaseModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="purchaseModalLabel">구매 방법을 선택해주세요</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body justify-content center">
          <button type="button" class="btn btn-primary" onclick="javascript:location.href='/es/payment/view?productId=${dto.productId}'">바로구매</button>
          <button type="button" class="btn btn-primary">직거래</button>
        </div>
      </div>
    </div>
  </div>
  <c:choose>
    <c:when test="${isLiked}">
      <img src="../../../resources/images/heart/heartRed.png" id="heartImageRed" height="30px" width="30px" onclick="Heart()">
    </c:when>
    <c:otherwise>
      <img src="../../../resources/images/heart/heart.png" id="heartImage" height="30px" width="30px" onclick="Heart()">
    </c:otherwise>
  </c:choose>
</section>
<%@include file="/main/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
<script>
  function Heart() {
    const heartImageRed = document.getElementById("heartImageRed");
    const heartImage = document.getElementById("heartImage");
    const productId = ${dto.productId}
    const userId = "user1" //우선 고정값
    if (heartImageRed) {
      // 현재 빨간 하트인 경우 (좋아요를 취소하는 경우)
      window.location.href = "/es/like/delete?productId=" + productId + "&userId=" + userId;
      heartImageRed.src = "../../../resources/images/heart/heart.png";
    } else if (heartImage) {
      // 현재 빈 하트인 경우 (좋아요를 추가하는 경우)
      window.location.href = "/es/like/insert?productId=" + productId + "&userId=" + userId;
      heartImage.src = "../../../resources/images/heart/heartRed.png";
    }
  }
</script>

