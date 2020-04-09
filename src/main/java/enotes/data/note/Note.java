package enotes.data.note;

import enotes.data.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "notes")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Note implements Serializable {
    @Id
    @Positive
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @NotBlank
    @Size(max = 500)
    private String header;

    @Size(max = 5000)
    private String body;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NoteState state;

    @PastOrPresent
    @NotNull
    private LocalDate created;
    @PastOrPresent
    private LocalDate lastModified;
    @PastOrPresent
    private LocalDate archivedAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isDeleted;
    private boolean isArchived;
    private boolean isDone;
}
