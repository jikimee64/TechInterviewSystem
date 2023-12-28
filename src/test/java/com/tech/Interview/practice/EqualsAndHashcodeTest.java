package com.tech.Interview.practice;

import com.tech.Interview.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

// https://velog.io/@park2348190/JPA-Entity%EC%9D%98-equals%EC%99%80-hashCode
@SpringBootTest
public class EqualsAndHashcodeTest {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Test
    @DisplayName("엔티티 비교에서 프록시 객체의 여부와 상관 없이 id 같다면 같은 entity 여야한다.")
    void product_equals() {
        User user = getPersistUser("jikimee64", "jikimee64@gmail.com");
        User proxyUser = getProxyUser(user.getId());
        assertThat(user).isEqualTo(proxyUser);
        assertThat(proxyUser).isEqualTo(user);
    }

    private User getPersistUser(String userId, String email) {
        User user = new User(userId, email);
        entityManager.persist(user);
        entityManager.flush();
        entityManager.clear(); // 영속성 컨텍스트를 비움
        return user;
    }

    private User getProxyUser(Long userId) {
        return entityManager.getReference(User.class, userId);
    }

}
