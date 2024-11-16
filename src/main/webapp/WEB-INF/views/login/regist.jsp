<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>회원가입</title>
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
      max-width: 800px;
      margin: 50px auto;
      padding: 20px;
      background-color: #fff;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
    }

    h1 {
      text-align: center;
      font-size: 24px;
      margin-bottom: 20px;
    }

    label {
      font-weight: bold;
      margin-top: 10px;
      display: block;
    }

    input[type="text"], input[type="password"], input[type="email"], select {
      width: 100%;
      padding: 10px;
      margin: 5px 0 10px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    button {
      width: 100%;
      padding: 10px;
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
    }

    button:hover {
      background-color: #555;
    }

    .error-message {
      color: red;
      font-size: 12px;
      display: none; /* display 속성은 JavaScript에서 제어 */
    }

    option{
      color: black;
    }
    input[type="button"] {
      background-color: rgb(0, 0, 0); /* 버튼 배경 색상 */
      color: white; /* 텍스트 색상 */
      border: none; /* 테두리 제거 */
      padding: 10px 20px; /* 여백 */
      font-size: 16px; /* 폰트 크기 */
      border-radius: 5px; /* 모서리 둥글게 */
      cursor: pointer; /* 커서를 포인터로 변경 */
      transition: background-color 0.3s ease; /* 배경색 전환 효과 */
    }

    input[type="button"]:hover {
      background-color: rgba(0, 0, 0, 0.66); /* 호버 시 배경색 */
    }

    input[type="button"]:active {
      background-color: rgba(66, 66, 66, 0.66); /* 클릭 시 배경색 */
    }

  </style>
</head>
<body>
<%@include file="../main/header.jsp"%>
<c:if test="${not empty errorMessage}">
  <script>
    alert("${errorMessage}");
  </script>
</c:if>

<div class="privacy-policy">
  <h1>회원가입</h1>
  <form id="registrationForm" onsubmit="return validateForm()" method="post">
    <label for="userId">아이디</label>
    <input type="text" id="userId" name="userId" placeholder="영문, 숫자 포함 6~20자 입력해주세요.">
    <p class="error-message" id="userIdError">아이디를 입력해주세요.</p>
    <!--<input type="button" value="아이디 중복 확인" />-->

    <label for="password">비밀번호</label>
    <input type="password" id="password" name="userPw" placeholder="영문, 숫자, 특수문자(@#$%^&+=) 포함 8~20자 입력해주세요.">
    <p class="error-message" id="passwordError">비밀번호를 올바르게 입력해주세요.</p>

    <label for="confirmPassword">비밀번호 확인</label>
    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호를 다시 한번 입력해주세요.">
    <p class="error-message" id="confirmPasswordError">비밀번호가 일치하지 않습니다.</p>

    <label for="name">이름</label>
    <input type="text" id="name" name="userName" placeholder="이름을 입력해주세요.">
    <p class="error-message" id="nameError">이름을 입력해주세요.</p>

    <label for="phone">휴대전화번호</label>
    <input type="text" id="phone" name="userPhone" placeholder="숫자만 입력해주세요.">
    <p class="error-message" id="phoneError">휴대전화번호를 입력해주세요.</p>

    <label for="email">이메일</label>
    <input type="email" id="email" name="userEmail" placeholder="@ 기호 다음에는 알파벳과 숫자로 이루어진 도메인이 오게 해주세요">
    <p class="error-message" id="emailError">이메일을 입력해주세요.</p>

    <label for="birth">생년월일</label>
    <select id="year" name="year" onchange="updateMonths()">
      <option selected disabled>연도 선택</option>
      <c:forEach begin="1950" end="2017" var="i">
        <option value="${i}">${i}</option>
      </c:forEach>
    </select>
    <select id="month" name="month" onchange="updateDays()" disabled>
      <option selected disabled>월 선택</option>
      <option value="01">1</option>
      <option value="02">2</option>
      <option value="03">3</option>
      <option value="04">4</option>
      <option value="05">5</option>
      <option value="06">6</option>
      <option value="07">7</option>
      <option value="08">8</option>
      <option value="09">9</option>
      <option value="10">10</option>
      <option value="11">11</option>
      <option value="12">12</option>
    </select>
    <select id="day" name="day" onchange="updateBirth();" disabled>
      <option selected disabled>일 선택</option>
    </select>
    <input type="text" id="birth" name="userBirth" hidden="hidden" value="" readonly>

    <label for="userPostcode">우편번호:</label>
    <input type="text" id="userPostcode" name="userPostcode" readonly required>
    <div id="div_err_zipCode" style="display: none;"></div>
    <input type="button" onclick="goZip()" value="우편번호 찾기" />

    <label for="userAddr">주소:</label>
    <input type="text" id="userAddr" name="userAddress" readonly required>

    <label for="userJibunAddr">지번 주소:</label>
    <input type="text" id="userJibunAddr" name="userJibunAddr" readonly>

    <div id="guide" style="display: none; color: #999; margin-top: 10px;"></div>

    <button type="submit">회원가입</button>
  </form>
</div>

<%@include file="../main/footer.jsp"%>

<script>
  function validateForm() {
    let isValid = true;

    const userId = document.getElementById('userId').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const name = document.getElementById('name').value.trim();
    const userPostcode = document.getElementById('userPostcode').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const email = document.getElementById('email').value.trim();

    // 모든 오류 메시지 숨김
    document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');

    // 아이디 유효성 검사
    const idPattern = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/;
    if (!idPattern.test(userId)) {
      document.getElementById('userIdError').style.display = 'block';
      isValid = false;
    }

    // 비밀번호 유효성 검사
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,20}$/;
    if (!passwordPattern.test(password)) {
      document.getElementById('passwordError').style.display = 'block';
      isValid = false;
    }

    // 비밀번호 일치 확인
    if (password !== confirmPassword) {
      document.getElementById('confirmPasswordError').style.display = 'block';
      isValid = false;
    }

    // 이름 필수 입력 확인
    if (!name) {
      document.getElementById('nameError').style.display = 'block';
      isValid = false;
    }

    // 우편번호 필수 입력 확인
    if (!userPostcode) {
      document.getElementById('div_err_zipCode').style.display = 'block';
      isValid = false;
    }

    // 휴대전화번호 유효성 검사
    const phonePattern = /^[0-9]+$/;
    if (!phonePattern.test(phone)) {
      document.getElementById('phoneError').style.display = 'block';
      isValid = false;
    }

    // 이메일 유효성 검사
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email)) {
      document.getElementById('emailError').style.display = 'block';
      isValid = false;
    }

    return isValid;
  }


  function updateMonths() {
    const year = document.getElementById("year").value;
    const monthSelect = document.getElementById("month");

    monthSelect.selectedIndex = 0;
    updateDays(); // 일 선택 초기화

    monthSelect.disabled = !year;
  }

  function updateDays() {
    const year = document.getElementById("year").value;
    const month = document.getElementById("month").value;
    const daySelect = document.getElementById("day");

    daySelect.innerHTML = '<option selected disabled>일 선택</option>'; // 일 초기화

    if (year && month) {
      const daysInMonth = new Date(year, month, 0).getDate(); // 해당 월의 일 수 계산
      for (let day = 1; day <= daysInMonth; day++) {
        const dayValue = day < 10 ? '0' + day : day;
        daySelect.innerHTML += '<option value="' + (day < 10 ? '0' + day : day) + '">' + day + '</option>';
      }
      daySelect.disabled = false;
    } else {
      daySelect.disabled = true;
    }
  }

  function updateBirth() {
    const year = document.getElementById('year').value;
    const month = document.getElementById('month').value;
    const day = document.getElementById('day').value;
    const birth = document.getElementById('birth');

    // Set the birth field if all values are selected
    if (year && month && day) {
      birth.value = year + "-" + month + "-" + day;
    } else {
      birth.value = '';  // Clear birth field if any part is missing
    }
  }
</script>
  <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
  function goZip() {
    new daum.Postcode({
      oncomplete: function (data) {
        var roadAddr = data.roadAddress; // 도로명 주소
        var jibunAddr = data.jibunAddress; // 지번 주소
        var extraRoadAddr = ""; // 참고 항목

        // 법정동명이 있을 경우 추가
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraRoadAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraRoadAddr += (extraRoadAddr !== "" ? ", " + data.buildingName : data.buildingName);
        }
        // 참고항목이 있을 경우 괄호까지 추가
        if (extraRoadAddr !== "") {
          extraRoadAddr = " (" + extraRoadAddr + ")";
        }

        // 우편번호와 주소 정보를 해당 필드에 넣음
        document.getElementById("userPostcode").value = data.zonecode;
        document.getElementById("userAddr").value = roadAddr + extraRoadAddr;
        document.getElementById("userJibunAddr").value = jibunAddr;

        var guideTextBox = document.getElementById("guide");
        if (data.autoRoadAddress) {
          var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
          guideTextBox.innerHTML = "(예상 도로명 주소 : " + expRoadAddr + ")";
          guideTextBox.style.display = "block";
        } else if (data.autoJibunAddress) {
          var expJibunAddr = data.autoJibunAddress;
          guideTextBox.innerHTML = "(예상 지번 주소 : " + expJibunAddr + ")";
          guideTextBox.style.display = "block";
        } else {
          guideTextBox.innerHTML = "";
          guideTextBox.style.display = "none";
        }
      },
    }).open();
  }
</script>

</body>
</html>
