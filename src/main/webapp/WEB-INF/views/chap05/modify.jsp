<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 글쓰기</title>

    <%-- 웹페이지의 고정 헤더를 만들어서 페이지마다 적용하기 (모듈화) --%>
    <%@ include file="../include/static-head.jsp" %>

</head>

<body>

<%@ include file="../include/static-head.jsp" %>

<div id="wrap" class="form-container">
    <form action="/board/modify" method="post">
        <input type="hidden" name="bno" value="${b.boardNo}">
        <h1>${b.boardNo}번 게시물 내용~ </h1>
        <h2># 작성일자: ${b.date}</h2>
        <label for="title">제목</label>
        <input type="text" id="title" maxlength="80" name="title" value="${b.title}">
        <label for="content">내용</label>
        <textarea id="content" name="content" maxlength="2000" required>${b.content}</textarea>
        <div class="buttons">
            <button class="list-btn" type="button" onclick="history.back()">이전으로</button>
            <button class="list-btn" type="submit">수정완료</button>
        </div>
    </form>
</div>

<script>
    // input 내 엔터 입력시 자동 submit 방지
    document.addEventListener('keydown', function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
        }
    }, true);
</script>
</body>
</html>