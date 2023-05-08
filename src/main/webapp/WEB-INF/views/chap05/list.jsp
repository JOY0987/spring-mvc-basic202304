<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Board List</title>

    <%--  모듈화  --%>
    <%@ include file="../include/static-head.jsp" %>

</head>

<body>

    <%@ include file="../include/static-head.jsp" %>

    <div id="wrap">

        <div class="main-title-wrapper">
            <h1 class="main-title">꾸러기 게시판</h1>
            <button class="add-btn">새 글 쓰기</button>
        </div>

        <div class="top-section">
            <!-- 검색창 영역 -->
            <div class="search">
                <form action="/board/list" method="get">

                    <select class="form-select" name="type" id="search-type">
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="writer">작성자</option>
                        <option value="tc">제목+내용</option>
                    </select>

                    <input type="text" class="form-control" name="keyword" value="${s.keyword}">

                    <button class="btn btn-primary" type="submit">
                        <i class="fas fa-search"></i>
                    </button>

                </form>
            </div>
        </div>

        <div class="card-container">

            <c:forEach var="b" items="${boardList}">
                <div class="card-wrapper">
                    <section class="card" data-bno="${b.boardNo}">
                        <div class="card-title-wrapper">
                            <h2 class="card-title">${b.shorTitle}</h2>
                            <div class="time-view-wrapper">
                                <div class="time"><i class="far fa-clock"></i>${b.date}</div>
                                <div class="like">
                                    <i class="fas fa-heart"></i>
                                    <span class="like-count">${b.likeCount}</span>
                                </div>
                                <div class="view">
                                    <i class="fas fa-eye"></i>
                                    <span class="view-count">${b.viewCount}</span>
                                </div>
                            </div>
                        </div>
                        <div class="card-content">
                                ${b.shortContent}
                        </div>
                    </section>
                    <div class="card-btn-group">
                        <button class="del-btn" data-href="/board/delete?bno=${b.boardNo}">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                </div>
            </c:forEach>

        </div>
        <!-- end card container -->

        <!-- 게시글 목록 하단 영역 -->
        <div class="bottom-section">

            <!-- 페이지 버튼 영역 -->
            <nav aria-label="Page navigation example">
                <ul class="pagination pagination-lg pagination-custom">

                    <c:if test="${maker.page.pageNo != 1}">
                        <li class="page-item"><a class="page-link" href="/board/list?pageNo=1">&lt;&lt;</a></li>
                    </c:if>

                    <c:if test="${maker.prev}">
                        <li class="page-item"><a class="page-link" href="/board/list?pageNo=${maker.begin - 1}&type=${s.type}&keyword=${s.keyword}">prev</a></li>
                    </c:if>

                    <c:forEach var="i" begin="${maker.begin}" end="${maker.end}">
                        <li data-page-num="${i}" class="page-item">
                            <a class="page-link" href="/board/list?pageNo=${i}&type=${s.type}&keyword=${s.keyword}">${i}</a>
                        </li>
                    </c:forEach>

                    <c:if test="${maker.next}">
                        <li class="page-item"><a class="page-link" href="/board/list?pageNo=${maker.end + 1}&type=${s.type}&keyword=${s.keyword}">next</a></li>
                    </c:if>

                    <c:if test="${maker.page.pageNo != maker.finalPage}">
                        <li class="page-item"><a class="page-link" href="/board/list?pageNo=${maker.finalPage}&type=${s.type}&keyword=${s.keyword}">&gt;&gt;</a></li>
                    </c:if>
                </ul>
            </nav>

        </div>

    </div>

    <!-- 모달 창 -->
    <div class="modal" id="modal">
        <div class="modal-content">
            <p>정말로 삭제할까요?</p>
            <div class="modal-buttons">
                <button class="confirm" id="confirmDelete"><i class="fas fa-check"></i> 예</button>
                <button class="cancel" id="cancelDelete"><i class="fas fa-times"></i> 아니오</button>
            </div>
        </div>
    </div>

    <script>
        const $cardContainer = document.querySelector('.card-container');
        //================= 삭제버튼 스크립트 =================//
        const modal = document.getElementById('modal'); // 모달창 얻기
        const confirmDelete = document.getElementById('confirmDelete'); // 모달 삭제 확인버튼
        const cancelDelete = document.getElementById('cancelDelete'); // 모달 삭제 취소 버튼

        $cardContainer.addEventListener('click', e => {
            // 삭제 버튼을 눌렀다면~
            if (e.target.matches('.card-btn-group *')) {
                console.log('삭제버튼 클릭');
                modal.style.display = 'flex'; // 모달 창 띄움
                const $delBtn = e.target.closest('.del-btn');
                const deleteLocation = $delBtn.dataset.href;
                // 확인 버튼 이벤트
                confirmDelete.onclick = e => {
                    // 삭제 처리 로직
                    window.location.href = deleteLocation;
                    modal.style.display = 'none'; // 모달 창 닫기
                };
                // 취소 버튼 이벤트
                cancelDelete.onclick = e => {
                    modal.style.display = 'none'; // 모달 창 닫기
                };
            } else { // 삭제 버튼 이외를 누르면 상세 조회 요청
                // section 태그에 붙은 글 번호 읽기
                const bno = e.target.closest('section.card').dataset.bno;
                // 상세 조회 요청 보내기
                window.location.href = '/board/detail?bno=' + bno + '&pageNo=${s.pageNo}&type=${s.type}&keyword=${s.keyword}';
            }
        });
        // 전역 이벤트로 모달창 닫기
        window.addEventListener('click', e => {
            if (e.target === modal) {
                modal.style.display = 'none';
            }
        });
        //========== 게시물 목록 스크립트 ============//
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

        $cardContainer.onmouseover = e => {
            if (!e.target.matches('.card-container *')) return;
            const $targetCard = e.target.closest('.card');
            $targetCard?.classList.add('card-hover');
            const $delBtn = e.target.closest('.card-wrapper')?.querySelector('.del-btn');
            $delBtn.style.opacity = '1';
        }
        $cardContainer.onmousedown = e => {
            if (e.target.matches('.card-container .card-btn-group *')) return;
            const $targetCard = e.target.closest('.card-wrapper');
            $targetCard?.setAttribute('id', 'card-down');
        };
        $cardContainer.onmouseup = removeDown;
        $cardContainer.addEventListener('mouseout', removeDown);
        $cardContainer.addEventListener('mouseout', removeHover);
        // write button event
        document.querySelector('.add-btn').onclick = e => {
            window.location.href = '/board/write';
        };

        //현재 위치한 페이지에 active 스타일 부여하기
        function appendPageActive() {

            // 현재 내가 보고 있는 페이지 넘버
            const curPageNum = '${maker.page.pageNo}';
            // console.log("현재페이지: ", curPageNum);

            // 페이지 li 태그들을 전부 확인해서
            // 현재 위치한 페이지 넘버와 텍스트컨텐츠가 일치하는
            // li를 찾아서 class active 부여
            const $ul = document.querySelector('.pagination');

            for (let $li of [...$ul.children]) {
                if (curPageNum === $li.dataset.pageNum) {
                    $li.classList.add('active');
                    break;
                }
            }
        }

        // 셀렉트옵션 검색타입 태그 고정
        function fixSearchOption() {
            const $select = document.getElementById('search-type');

            for (let $opt of [...$select.children]) {
                if ($opt.value === '${s.type}') {
                    $opt.setAttribute('selected', 'selected');
                    break;
                }
            }
        }

        appendPageActive();
        fixSearchOption();

    </script>


</body>

</html>