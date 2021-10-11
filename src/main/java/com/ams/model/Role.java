package com.ams.model;

import com.ams.security.SimpleGrantedAuthorityConverter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "t_role")
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "right_ids")
    @CollectionTable(name = "t_right", joinColumns = @JoinColumn(name = "id"))
    @Convert(converter = SimpleGrantedAuthorityConverter.class)
    private List<SimpleGrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
