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
      display: none;
    }
    option{
      color: black;
    }
  </style>
</head>
<body>
<%@include file="/main/header.jsp"%>

<div class="privacy-policy">
  <h1>회원가입</h1>
  <form id="registrationForm" onsubmit="return validateForm()">
    <label for="userId">아이디</label>
    <input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요.">
    <p class="error-message" id="userIdError">아이디를 입력해주세요.</p>

    <label for="password">비밀번호</label>
    <input type="password" id="password" name="password" placeholder="영문, 숫자, 특수문자(@#$%^&+=) 포함 8~20자 입력해주세요.">
    <p class="error-message" id="passwordError">비밀번호를 올바르게 입력해주세요.</p>

    <label for="confirmPassword">비밀번호 확인</label>
    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호를 다시 한번 입력해주세요.">
    <p class="error-message" id="confirmPasswordError">비밀번호가 일치하지 않습니다.</p>

    <label for="name">이름</label>
    <input type="text" id="name" name="name" placeholder="이름을 입력해주세요.">
    <p class="error-message" id="nameError">이름을 입력해주세요.</p>

    <label for="addr">주소</label>
    <input type="text" id="addr" name="addr" placeholder="주소를 입력해주세요.">
    <p class="error-message" id="addrError">주소를 입력해주세요.</p>

    <label>생년월일</label>
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
    <select id="day" name="day" disabled>
      <option selected disabled>일 선택</option>
    </select>

    <label for="phone">휴대전화번호</label>
    <input type="text" id="phone" name="phone" placeholder="숫자만 입력해주세요.">
    <p class="error-message" id="phoneError">휴대전화번호를 입력해주세요.</p>

    <label for="email">이메일</label>
    <input type="email" id="email" name="email" placeholder="이메일을 입력해주세요.">
    <p class="error-message" id="emailError">이메일을 입력해주세요.</p>

    <button type="submit">회원가입</button>
  </form>
</div>

<%@include file="/main/footer.jsp"%>

<script>
  function validateForm() {
    let isValid = true;

    const userId = document.getElementById('userId').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const name = document.getElementById('name').value.trim();
    const addr = document.getElementById('addr').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const email = document.getElementById('email').value.trim();

    document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');

    if (!userId) {
      document.getElementById('userIdError').style.display = 'block';
      isValid = false;
    }

    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,20}$/;
    if (!passwordPattern.test(password)) {
      document.getElementById('passwordError').style.display = 'block';
      isValid = false;
    }

    if (password !== confirmPassword) {
      document.getElementById('confirmPasswordError').style.display = 'block';
      isValid = false;
    }

    if (!name) {
      document.getElementById('nameError').style.display = 'block';
      isValid = false;
    }

    if (!addr) {
      document.getElementById('addrError').style.display = 'block';
      isValid = false;
    }

    const phonePattern = /^[0-9]+$/;
    if (!phonePattern.test(phone)) {
      document.getElementById('phoneError').style.display = 'block';
      isValid = false;
    }

    if (!email) {
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
        daySelect.innerHTML += '<option value="' +(day < 10 ? '0' + day : day)+'">'+day+'</option>';
      }
      daySelect.disabled = false;
    } else {
      daySelect.disabled = true;
    }
  }
</script>
</body>
</html>
