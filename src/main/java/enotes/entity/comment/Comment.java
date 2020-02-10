package enotes.entity.comment;

import java.io.Serializable;

import enotes.entity.note.Note;
import enotes.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter @Setter
@EqualsAndHashCode(of = {"text", "user", "note", "version"})
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    private String text;
    @ManyToOne
    @JoinTable(name = "user_id")
    private User user;
    @ManyToOne
    @JoinTable(name = "note_id")
    private Note note;
}
