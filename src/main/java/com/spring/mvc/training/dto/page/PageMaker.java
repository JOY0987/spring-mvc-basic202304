package com.spring.mvc.training.dto.page;

public class PageMaker {
    // 한번에 그려낼 페이지 수
    private static final int PAGE_COUNT =  5;

    // 화면 렌더링 시 페이지의 시작값과 끝값, 맨 마지막 페이지값
    private int begin, end, finalPage;

    // 이전, 다음 버튼 활성화 여부
    private boolean prev, next;

    // 현재 요청 페이지 정보
    private Page page;

    // 총 게시글 수
    private int totalCount;

    // 생성자
    public PageMaker(Page page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;
    }

    // 페이지 계산 알고리즘
    private void makePageInfo() {
        // 1. end 값 계산
        // 올림처리 (현재 위치한 페이지번호 / 한 화면에 배치할 페이지수) * 한 화면에 배치할 페이지수
        this.end = (int) Math.ceil(page.getPageNo() / (double) PAGE_COUNT) * PAGE_COUNT;

        // 2. begin 값 계산
        this.begin = this.end = PAGE_COUNT + 1;

        // 끝 페이지 보정
        finalPage = (int) Math.ceil((double) totalCount / page.getAmount());

        // 마지막 페이지 구간에서만 엔드 보정이 일어나야 함
        if (finalPage < this.end) this.end = finalPage;

        // 이전 버튼 활성화 여부
        this.prev = begin > 1; // 1일 때만 false

        // 다음 버튼 활성화 여부
        this.next = end < finalPage; // 페이지의 끝값이 맨 마지막 페이지보다 작을 때만 true (버튼 활성화)
    }
}
