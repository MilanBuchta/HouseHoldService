package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@JsonTypeName("string")
public class DataString extends AbstractData<String> {


    @Column(name = "string_value")
    private String value;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void convertValue(String input) {
        setValue(input);
    }
}
