package sk.fri.uniza.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;
import javax.validation.constraints.Pattern;

@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "HouseHold_findByZip",
                query = "from HouseHold where zip = :zipNo"),
        @org.hibernate.annotations.NamedQuery(
                name = "HouseHold_findByFirstName",
                query = "from HouseHold where firstname = :name"),
        @org.hibernate.annotations.NamedQuery(name = "HouseHold_findLastName",
                query = "from HouseHold where lastname = :name"),
        @org.hibernate.annotations.NamedQuery(name = "HouseHold_findAll",
                query = "from HouseHold"),

})
@Entity
public class HouseHold {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    // Swagger nebude zobrazovať atribút
    private Long id;
    @NotEmpty
    @ApiModelProperty(example = "Univerzitná") // Príklad pre swagger doku.
    private String street;
    @NotEmpty
    @ApiModelProperty(example = "Žilina") // Príklad pre swagger doku.
    private String city;
    @NotEmpty
    @ApiModelProperty(example = "Slovakia") // Príklad pre swagger doku.
    private String state;
    @NotEmpty
    @Pattern(regexp = "^\\d*$")
    @ApiModelProperty(example = "01008") // Príklad pre swagger doku.
    private String zip;
    @Valid
    private ContactPerson contactPerson;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "houseHold")
    @JsonIgnore // Ignorovanie danej premenej z pohladu Serializacie do
    // Objektu JSON.Gneroval by sa obrovský JSON a dochádzalo by aj k zacykleniu
    private Set<AbstractData> data;
    @Transient
    @JsonIgnore // Ignorovanie danej premenej z pohladu Serializacie do
    // Objektu JSON.Gneroval by sa obrovský JSON a dochádzalo by aj k zacykleniu
    private Collection<IotNode> iotNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Set<AbstractData> getData() {
        return data;
    }

    public Collection<IotNode> getIotNode() {
        return iotNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseHold houseHold = (HouseHold) o;
        return id != null ? id.equals(houseHold.id) : houseHold.id == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

