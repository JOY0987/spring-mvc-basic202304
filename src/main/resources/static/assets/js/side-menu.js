//버튼의 요소 노드 취득
const menuBtn2 = document.querySelector('header .menu-open');
const closeBtn = document.querySelector('.gnb .close');

const gnb = document.querySelector('.gnb');

//클릭 이벤트 생성
menuBtn2.addEventListener('click', () => {
    gnb.classList.add('on');
});

closeBtn.addEventListener('click', () => {
    gnb.classList.remove('on');
});