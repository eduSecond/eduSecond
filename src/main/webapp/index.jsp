<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<ul>
    <li>게시판
        <ul>
            <li><a href="/product/list">목록</a> </li>
            <li><a href="/bbs/view.do?idx=1&pageNo=&pageSize=&sortField=idx&sortOrder=">조회</a> </li>
            <li><a href="/bbs/reigst.do">등록</a> </li>
            <li><a href="/bbs/modfiy.do">수정</a> </li>
            <li><a href="/admin/login">회원관리</a> </li>
        </ul>
    </li>
</ul>
</body>
</html>