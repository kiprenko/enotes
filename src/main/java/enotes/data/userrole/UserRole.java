package enotes.data.userrole;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@Getter @Setter
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserRole implements Serializable {
    @Id
    @Positive
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String role;
}
