package com.tech.Interview.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata.RevisionType;

import static com.tech.Interview.user.UserTest.createUser;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 data envers 삽입, 수정, 삭제 히스토리 저장 테스트")
    void user_data_envers() {
        User user = createUser("userId", "email");
        User savedUser = userRepository.save(user);

        Revision<Long, User> insertRevision = userRepository.findRevision(1L, 1L).get();
        User insertUserHistory = insertRevision.getEntity();

        assertThat(insertRevision.getMetadata().getRevisionType()).isEqualTo(RevisionType.INSERT);
        assertThat(insertUserHistory.getUserId()).isEqualTo("userId");
        assertThat(insertUserHistory.getEmail()).isEqualTo("email");

        savedUser.update("updated email");
        userRepository.save(savedUser);

        Revision<Long, User> updateRevision = userRepository.findRevision(1L, 2L).get();
        User updateUserHistory = updateRevision.getEntity();

        assertThat(updateRevision.getMetadata().getRevisionType()).isEqualTo(RevisionType.UPDATE);
        assertThat(updateUserHistory.getEmail()).isEqualTo("updated email");

        userRepository.delete(user);

        Revision<Long, User> deleteRevision = userRepository.findRevision(1L, 3L).get();
        assertThat(deleteRevision.getMetadata().getRevisionType()).isEqualTo(RevisionType.DELETE);
    }

}
