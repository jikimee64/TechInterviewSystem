package com.tech.Interview.user;

import com.tech.Interview.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Table(name = "user")
public class User extends BaseEntity<User> {

    @Column(name = "user_id", length = 50, nullable = false, unique = true)
    private String userId;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Builder
    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public void update(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(userId, user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

}
