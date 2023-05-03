package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// jUnit4 기준으로 작성

@SpringBootTest
class ReplyMapperTest {

    @Autowired
    BoardMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;


    @Test
    @DisplayName("게시물 300개를 등록하고 각 게시물에 랜덤으로 1000개의 댓글을 나눠서 등록해야 한다.")
    void bulkInsertTest() {
        for (int i = 1; i <= 300; i++) {
            Board b = Board.builder()
                            .title("재밋는 게시물" + i)
                            .content("노잼 게시물 내용" + i)
                            .build();
            boardMapper.save(b);
        }
        assertEquals(300, boardMapper.count(new Search()));

        for (int i = 1; i <= 1000; i++) {
            Reply reply = Reply.builder()
                    .replyWriter("잼민이" + i)
                    .replyText("말똥잉" + i)
                    .boardNo((long) (Math.random() * 300 + 1))
                    .build();
            replyMapper.save(reply);
        }
    }

    // 단위 테스트
    @Test
    @DisplayName("댓글을 3번 게시물에 등록하면 3번 게시물의 총 댓글 수는 5개여야 한다")
    @Transactional
    @Rollback // 테스트 끝난 이후 롤백해라 (Transactional + Rollback) => 롤백을 안 시키면 테스트 할때마다 assert 비교값을 바꿔야함!
    void saveTest() {
        // given
        long boardNo = 3L;
        Reply newReply = Reply.builder()
                .replyText("세이브")
                .replyWriter("앙뇽")
                .boardNo(boardNo)
                .build();

        //when
        boolean flag = replyMapper.save(newReply);

        // then
        assertTrue(flag); // 세이브가 성공했을 것이라고 단언
        assertEquals(7, replyMapper.count(boardNo));
//        assertEquals("앙뇽", replyMapper.findOne(boardNo).getReplyWriter());
    }

    @Test
    @DisplayName("댓글 번호가 1001번인 댓글을 삭제하면 3번 게시물의 총 댓글 수는 5이어야 한다.")
    @Transactional
    @Rollback
    void removeTest() {
        // given
        long replyNo = 1001L;
        long boardNo = 3L;

        // when
        boolean flag = replyMapper.deleteOne(replyNo);

        // then
        assertTrue(flag);
        assertEquals(5, replyMapper.count(boardNo));
    }

    @Test
    @DisplayName("3번 댓글을 조회했을 때, 댓글 작성자의 이름은 잼민이3이어야 한다.")
    @Transactional
    @Rollback
    void findOneTest() {
        // given
        Reply reply = replyMapper.findOne(3L);
        // then
        assertEquals("잼민이3", reply.getReplyWriter());
    }

    @Test
    @DisplayName("1001번 댓글 내용을 '수정댐'으로 수정하고, 다시 조회했을 때 1001번 댓글의 내용은 '수정댐'이어야 한다.")
    @Transactional
    @Rollback
    void modifyTest() {

        Reply modifyReply = Reply.builder()
                .replyNo(1001L)
                .replyText("수정댐")
                .build();

        // when
        boolean flag = replyMapper.modify(modifyReply);

        // then
        assertTrue(flag);
        assertEquals("수정댐", replyMapper.findOne(1001L).getReplyText());
    }

    @Test
    @DisplayName("3번 게시글의 전체 댓글을 조회했을 때, 리스트의 길이는 6이어야 하고 0번 인덱스의 댓글 작성자가 잼민이34 여야 한다.")
    @Transactional
    @Rollback
    void findAllTest() {
        // given
        List<Reply> replyList = replyMapper.findAll(3L, new Page());

        // when

        // then
        assertEquals(6, replyList.size());
        assertEquals("잼민이34", replyList.get(0).getReplyWriter());
    }

    @Test
    void bulkReplyInsert() {
        for (int i = 0; i < 300; i++) {
            Reply reply = Reply.builder()
                    .replyText("페이지댓글내용" + i)
                    .replyWriter("망둥어")
                    .boardNo(298L)
                    .build();
            replyMapper.save(reply);
        }
    }
}