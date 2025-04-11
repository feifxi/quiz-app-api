package sit.int204.quizappapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Lob
    @Column(name = "role", nullable = false)
    private String role;

    @Lob
    @Column(name = "profile_img")
    private String profileImg;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<PlayerProgress> playerProgresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "createBy")
    private Set<Quiz> ownedQuizs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Reaction> reactions = new LinkedHashSet<>();

}