<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">

    <!-- reset -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- fontawesome css: https://fontawesome.com -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

    <link rel="stylesheet" href="/assets/css/main.css">
    <link rel="stylesheet" href="/assets/css/list.css">

</head>

<body>

<div id="wrap">

    <div class="main-title-wrapper">
        <h1 class="main-title">꾸러기 게시판</h1>
        <button class="add-btn">새 글 쓰기</button>
    </div>

    <div class="card-container">
        <div class="card-wrapper">
            <section class="card">
                <div class="card-title-wrapper">
                    <h2 class="card-title">룰루랄라~~1</h2>
                    <div class="time-view-wrapper">
                        <div class="time"><i class="far fa-clock"></i>2023-04-14 12:50</div>
                        <div class="view">
                            <i class="fas fa-eye"></i>
                            <span class="view-count">15</span>
                        </div>
                    </div>
                </div>
                <div class="card-content">
                    <p>
                        어쩌구 저쩌구 .... (30자 후 줄임처리)
                    </p>
                </div>
            </section>
            <div class="card-btn-group">
                <button class="del-btn">
                    <i class="fas fa-times"></i>
                </button>
            </div>
        </div>

        <div class="card-wrapper">
            <section class="card">
                <div class="card-title-wrapper">
                    <h2 class="card-title">룰루랄라~~2</h2>
                    <div class="time-view-wrapper">
                        <div class="time"><i class="far fa-clock"></i>2023-04-14 12:50</div>
                        <div class="view">
                            <i class="fas fa-eye"></i>
                            <span class="view-count">15</span>
                        </div>
                    </div>
                </div>
                <div class="card-content">
                    <p>
                        어쩌구 저쩌구 .... (30자 후 줄임처리)
                    </p>
                </div>
            </section>
            <div class="card-btn-group">
                <button class="del-btn">
                    <i class="fas fa-times"></i>
                </button>
            </div>
        </div>


        <div class="card-wrapper">
            <section class="card">
                <div class="card-title-wrapper">
                    <h2 class="card-title">룰루랄라~~</h2>
                    <div class="time-view-wrapper">
                        <div class="time"><i class="far fa-clock"></i>2023-04-14 12:50</div>
                        <div class="view">
                            <i class="fas fa-eye"></i>
                            <span class="view-count">15</span>
                        </div>
                    </div>
                </div>
                <div class="card-content">
                    <p>
                        어쩌구 저쩌구 .... (30자 후 줄임처리)
                    </p>
                </div>
            </section>
            <div class="card-btn-group">
                <button class="del-btn">
                    <i class="fas fa-times"></i>
                </button>
            </div>
        </div>

    </div>

</div>



<script>
    function removeDown(e) {
        if (!e.target.matches('.card-container *')) return;
        const $targetCard = e.target.closest('.card-wrapper');
        $targetCard?.removeAttribute('id', 'card-down');
    }

    function removeHover(e) {
        if (!e.target.matches('.card-container *')) return;
        const $targetCard = e.target.closest('.card');
        $targetCard?.classList.remove('card-hover');

        const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
        $delBtn.style.opacity = '0';
    }

    const $cardContainer = document.querySelector('.card-container');

    $cardContainer.onmouseover = e => {

        if (!e.target.matches('.card-container *')) return;

        const $targetCard = e.target.closest('.card');
        $targetCard?.classList.add('card-hover');

        const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
        $delBtn.style.opacity = '1';
    }

    $cardContainer.onmousedown = e => {

        if (!e.target.matches('.card-container *')) return;

        const $targetCard = e.target.closest('.card-wrapper');
        $targetCard?.setAttribute('id', 'card-down');
    };

    $cardContainer.onmouseup = removeDown;

    $cardContainer.addEventListener('mouseout', removeDown);
    $cardContainer.addEventListener('mouseout', removeHover);

    // write button event
    document.querySelector('.add-btn').onclick = e => {
        window.location.href = '/card/write';
    };

</script>

</body>

</html>