package ru.colibri.colibriserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "security_users")
public class User implements Serializable {
    private static final long serialVersionUID = -3218171099657412026L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name="Name")
    private String name;

    @NotEmpty
    @Column(name="username", unique = true, nullable = false)
    private String username;
    @NotEmpty

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private boolean enabled;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "security_user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Role> roles = new ArrayList<>();


    public User(String name, @NotEmpty String username, @NotEmpty String password, boolean enabled) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User() {
    }
}