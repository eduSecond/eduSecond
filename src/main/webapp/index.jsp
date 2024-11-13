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
            <li><a href="/product/regist">등록</a> </li>
            <li><a href="/bbs/modfiy.do">수정</a> </li>
            <li><a href="/admin/login">회원관리</a> </li>
            <li><a href="/main/main">main</a></li>
            <li><a href="/message/chatting" target="_blank">채팅</a></li>
            <li><a href="/message/messageList">메시지 리스트</a></li>
        </ul>
    </li>
</ul>
</body>
</html>