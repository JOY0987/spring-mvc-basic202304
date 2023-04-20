<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <form method="get">
        <div class="wrap">
            <h1>${stu.name}님 성적 정보</h1>
            <div class="score-list">
                <span># 국어: ${stu.kor}점</span>
                <span># 영어: ${stu.eng}점</span>
                <span># 수학: ${stu.math}점</span>
                <span># 총점: ${stu.total}점</span>
                <span># 평균: ${stu.average}점</span>
                <span># 학점: ${stu.grade}</span>
            </div>
            <input type="hidden" name="stuNum" value="${stu.stuNum}">
            <button type="submit" formaction="/score/list" class="back-list-btn">목록</button>
            <button type="submit" formaction="/score/modify" class="modify-btn">수정</button>
        </div>
    </form>

</body>
</html>