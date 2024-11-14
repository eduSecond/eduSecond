<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    .container {
      width: 80%;
      margin: 50px auto;
      text-align: center;
    }

    h1 {
      font-size: 24px;
      margin-bottom: 20px;
    }

    .announcement-table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    .announcement-table th,
    .announcement-table td {
      border: 1px solid #ddd;
      padding: 12px;
      text-align: left;
    }

    .announcement-table th {
      background-color: #f2f2f2;
      font-weight: bold;
    }

    .announcement-table tbody tr:nth-child(even) {
      background-color: #fafafa;
    }

    .new-icon {
      color: red;
      margin-right: 5px;
    }

    .pagination {
      display: inline-block;
      margin: 20px 0;
    }

    .pagination a {
      color: #333;
      padding: 8px 16px;
      text-decoration: none;
      border: 1px solid #ddd;
      margin: 0 4px;
      border-radius: 4px;
    }

    .pagination a:hover {
      background-color: #ddd;
    }

    .search-box {
      margin-top: 20px;
    }

    .search-box select,
    .search-box input,
    .search-box button {
      padding: 10px;
      margin-right: 5px;
      font-size: 16px;
    }

    .search-box button {
      background-color: #333;
      color: #fff;
      border: none;
      cursor: pointer;
    }

    .search-box button:hover {
      background-color: #555;
    }

  </style>
</head>
<body>
<%@ include file="/main/header.jsp"%>

<section class="privacy-policy">
  <div class="container">
    <h1>공지사항</h1>
    <table class="announcement-table">
      <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>등록일</th>
        <th>조회수</th>

      </tr>
      </thead>
      <tbody>
      <c:forEach items="${notices}" var="notice">
        <tr>
          <td>${notice.noticeId}</td>
          <td>
            <a href="/admin/notice/view?noticeId=${notice.noticeId}">
                ${notice.title}
            </a>
          </td>
          <td>${notice.regDate}</td>
          <td>${notice.viewCount}</td>
        </tr>
        <!-- Add more rows as needed -->
      </c:forEach>
      </tbody>
    </table>
    <div class="pagination">
      <%@ include file="../common/paging.jsp" %>
    </div>

    <div class="search-box">
      <form class="search-box" method="get" action="/notice/noticelist">
        <select name="searchType" class="form-select" style="width: 120px;">
          <option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
          <option value="content" ${searchType == 'content' ? 'selected' : ''}>내용</option>
        </select>
        <input type="text" name="searchValue" value="${searchValue}" class="form-control" placeholder="검색어 입력">
        <button type="submit" class="btn btn-dark">검색</button>
      </form>
    </div>
  </div>

</section>


<%@include file="/main/footer.jsp"%>


</body>
</html>
