package enotes.data.user;

import enotes.data.userrole.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter @Setter
@EqualsAndHashCode(exclude = {"password", "decryptedPassword"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password", "decryptedPassword"})
@Builder
public class User implements Serializable {
    @Id
    @Positive
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @NotBlank
    @Size(max = 25)
    private String firstName;

    @NotBlank
    @Size(max = 25)
    private String lastName;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 2000)
    private String password;

    @Transient
    private String decryptedPassword;

    @Min(3)
    @Max(100)
    private int age;

    @NotBlank
    @Size(max = 25)
    private String country;

    @PastOrPresent
    @NotNull
    private LocalDate registration;

    private boolean active = true;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;
}
