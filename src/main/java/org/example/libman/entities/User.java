package org.example.libman.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private String username;

    @Column(unique = true, length = 100, nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String password;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, name = "created_at")
    @NonNull
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @NonNull
    private Date updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // TODO determine logic for these methods
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // TODO determine logic for these methods
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // TODO determine logic for these methods
    }

    @Override
    public boolean isEnabled() {
        return true; // TODO determine logic for these methods
    }
}
