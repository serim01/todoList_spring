package com.sparta.todolist;

import com.sparta.todolist.entity.TodoList;
import com.sparta.todolist.repository.TodoListRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
public class TransactionTest {
    @PersistenceContext
    EntityManager em;

    @Autowired //주입
    TodoListRepository todoListRepository;

    @Test
    @Transactional
    @Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
    @DisplayName("일정 생성 성공")
    void test1() {
        TodoList todo = new TodoList();
        todo.setUsername("serim");
        todo.setTitle("테스트");
        todo.setContents("@Transactional 테스트 중!");
        todo.setPassword("123456");

        em.persist(todo);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }
}
