package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "Field_All",
                query = "from Field"),
})

@Entity
public class Field {
    @Id
    @NaturalId //Primárny klúč nie je generovaný, ale je tvorený názvom premennej
    @NotEmpty
    private String name;

    private String unit;
    @NotEmpty
    private String descripton;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "field", cascade = CascadeType.REMOVE)
    @JsonIgnore // Ignorovanie danej premenej z pohladu Serializacie do
    // objektu JSON.Generoval by sa obrovský JSON a dochádzalo by aj k
    // zacykleniu
    private Set<AbstractData> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set<AbstractData> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        return name != null ? name.equals(field.name) : field.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
}
