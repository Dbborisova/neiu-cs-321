package cwm.security;

import cwm.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @NotNull
    @Size(min=5, message = "Username must have at least 5 characters")
    private String username;

    @NotNull
    @Size(min=5, message = "Password must have at least 5 characters")
    private String password;

    @NotNull
    @NotEmpty(message = "Name is required")
    private String fullName;

    @NotNull
    @Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{4}",message = "Phone number format:XXX-XXX-XXXX")
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username,passwordEncoder.encode(password),fullName,phone);
    }




}
