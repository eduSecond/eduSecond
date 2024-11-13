<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>공지사항 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="../common/adminHeader.jsp" %>
    
    <div class="container mt-4">
        <h2>공지사항 수정</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        
        <form action="/admin/notice/modify" method="post">
            <input type="hidden" name="noticeId" value="${notice.noticeId}">
            <input type="hidden" name="adminId" value="${notice.adminId}">
            
            <div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="title" name="title" 
                       value="${notice.title}" required>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="content" name="content" 
                          rows="10" required>${notice.content}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">수정</button>
            <a href="/admin/notice/view?noticeId=${notice.noticeId}" 
               class="btn btn-secondary">취소</a>
        </form>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>