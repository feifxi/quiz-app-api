package sit.int204.quizappapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "quizs")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 30)
    private String title;

    @Lob
    @Column(name = "thumbnail")
    private String thumbnail;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "create_by", nullable = false)
    private User createBy;

    @OneToMany(mappedBy = "quiz")
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "quiz")
    private Set<Level> levels = new LinkedHashSet<>();

    @OneToMany(mappedBy = "quiz")
    private Set<PlayerProgress> playerProgresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "quiz")
    private Set<Reaction> reactions = new LinkedHashSet<>();

}