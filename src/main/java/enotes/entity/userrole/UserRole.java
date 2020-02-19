package enotes.entity.userrole;


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
    private Long id;

    private String role;
}
