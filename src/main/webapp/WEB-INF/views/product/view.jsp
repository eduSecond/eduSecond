<%--
  Created by IntelliJ IDEA.
  User: full5-4
  Date: 2024-11-12
  Time: 오전 11:02
  To change this template use File | Settings | File Templates.
--%>
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
  <img src="../../../resources/images/heart/heart.png" id="heartImage" height="30px" width="30px" onclick="Heart()">
</section>
<%@include file="/main/footer.jsp"%>
</body>
<script>
  function Heart() {
    const heartImage = document.getElementById("heartImage");
    const productId = "${dto.productId}"; // JavaScript 내에서 JSP 변수를 사용합니다.
    const userId = "tester1"; // 사용자의 ID를 설정합니다 (예시로 하드코딩한 상태입니다. 실제로는 세션 등에서 가져올 수 있음).

    // AJAX 요청 설정
    const xhr = new XMLHttpRequest();
    let url = "";

    // 하트가 빈 상태인지 채워진 상태인지 확인
    if (heartImage.src.includes("heart.png")) {
      // 좋아요 추가 요청
      url = "/like/insert";
      heartImage.src = "../../../resources/images/heart/heartRed.png"; // 채워진 하트로 이미지 변경
    } else {
      // 좋아요 삭제 요청
      url = "/like/delete";
      heartImage.src = "../../../resources/images/heart/heart.png"; // 빈 하트로 이미지 변경
    }

    // 비동기 요청 전송
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4) {
        if (xhr.status !== 200) {
          // 요청이 실패한 경우 하트 이미지를 원래 상태로 되돌립니다.
          if (url.includes("insert")) {
            heartImage.src = "../../../resources/images/heart/heart.png";
          } else {
            heartImage.src = "../../../resources/images/heart/heartRed.png";
          }
          alert("좋아요 요청에 실패했습니다. 다시 시도해주세요.");
        }
      }
    };
    xhr.send("userId=" + user1 + "&productId=" + ${dto.productId});
  }
</script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>