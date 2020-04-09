package enotes.dto.user;

import enotes.data.userrole.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
@EqualsAndHashCode(exclude = {"password", "decryptedPassword"})
@ToString(exclude = {"password", "decryptedPassword"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private Integer version;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String decryptedPassword;
    private int age;
    private String country;
    private LocalDate registration;
    private boolean active = true;
    private UserRoleEnum role;
}
