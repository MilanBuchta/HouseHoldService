package sk.fri.uniza.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

@Embeddable
public class ContactPerson {

    @NotNull
    @Size(min = 2, max = 20)
    @ApiModelProperty(example = "Janko") // Príklad pre swagger
    // dokumentáciu
    private String firstName;


    @NotNull
    @Size(min = 2, max = 20)
    @ApiModelProperty(example = "Hraško") // Príklad pre swagger
    // dokumentáciu
    private String lastName;

    @NotNull
    @Pattern(regexp = "^(\\d{4}[ ]\\d{3}[ ]\\d{3}|\\d{10})$")
    //napr. 0905 123 456 alebo 0905123456
    @ApiModelProperty(example = "0907 123 456") // Príklad pre swagger
    // dokumentáciu
    private String phone;

    @NotNull
    @Email
    @ApiModelProperty(example = "em@mail.sk") // Príklad pre swagger
    // dokumentáciu
    private String email;

    public ContactPerson() {
    }

    public ContactPerson(String firstName, String lastName, String phone,
                         String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
