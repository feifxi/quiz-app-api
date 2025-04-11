package sit.int204.quizappapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Lob
    @Column(name = "template")
    private String template;

    @Lob
    @Column(name = "question")
    private String question;

    @Lob
    @Column(name = "question_image")
    private String questionImage;

    @Lob
    @Column(name = "correct_choice")
    private String correctChoice;

    @Lob
    @Column(name = "incorrect_choice1")
    private String incorrectChoice1;

    @Lob
    @Column(name = "incorrect_choice2")
    private String incorrectChoice2;

    @Lob
    @Column(name = "incorrect_choice3")
    private String incorrectChoice3;

}