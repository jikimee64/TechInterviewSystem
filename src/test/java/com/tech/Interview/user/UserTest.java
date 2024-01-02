package com.tech.Interview.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    public static User createUser(String userId, String email) {
        return User.builder()
                .userId(userId)
                .email(email)
                .build();
    }

    @Test
    @DisplayName("성공 - 유저의 이메일을 수정할 수 있다.")
    void success_user_email_update() {
        User user = User.builder()
                .userId("jikimee64")
                .email("jikimee64@gmail.com")
                .build();

        user.update("apple64222@gmail.com");

        assertThat(new User("jikimee64", "apple64222@gmail.com")).isEqualTo(user);
    }

}
