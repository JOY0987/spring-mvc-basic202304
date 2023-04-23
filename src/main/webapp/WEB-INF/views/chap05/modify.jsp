<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 글쓰기</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">

    <!-- reset -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- fontawesome css: https://fontawesome.com -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

    <link rel="stylesheet" href="/assets/css/main.css">
    <link rel="stylesheet" href="/assets/css/detail.css">

</head>

<body>
<div id="wrap" class="form-container">
    <form action="/board/modify" method="post">
        <input type="hidden" name="bno" value="${b.boardNo}">
        <h1>${b.boardNo}번 게시물 내용~ </h1>
        <h2># 작성일자: ${b.date}</h2>
        <label for="title">제목</label>
        <input type="text" id="title" maxlength="40" name="title" value="${b.title}">
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