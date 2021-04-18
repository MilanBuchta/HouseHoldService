package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@JsonTypeName("integer")
public class DataInteger extends AbstractData<Integer> {

    @Column(name = "integer_value")
    private Integer Value;

    @Override
    public Integer getValue() {
        return Value;
    }

    @Override
    public void setValue(Integer value) {
        Value = value;
    }

    @Override
    public void convertValue(String input) {
        setValue(Integer.valueOf(input));
    }
}
