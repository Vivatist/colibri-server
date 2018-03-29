package ru.colibri.colibriserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "security_roles")
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = -1562723181766676778L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    Integer id;

    @Column(name = "role_name")
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    @Override
    public String getAuthority() {
        return this.role;
    }


    public Role(String role) {
        this.role = role;
    }
}
