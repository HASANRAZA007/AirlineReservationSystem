package com.ars.airlinereservationsystem.model;

import com.ars.airlinereservationsystem.model.util.Gender;
import com.ars.airlinereservationsystem.model.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name ="password", nullable = false)
    private String password;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "contact_number",nullable = false,length = 13)
    private Long number;
    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;
    @Column(name = "address", length = 200)
    private String address;
    @OneToMany(mappedBy = "user")
    private List<Booking> bookingList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
