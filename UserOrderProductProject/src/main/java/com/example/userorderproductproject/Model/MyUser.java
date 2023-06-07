package com.example.userorderproductproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@NoArgsConstructor
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String username;

    @NotEmpty(message = "The Password can Not be Empty")
    //  @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$")
    private String password;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null check (role='custmer' or role='admin')")
    private String role;

   //ONE-Many
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "myUser")
    private Set <MyOrder> myorderSet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
