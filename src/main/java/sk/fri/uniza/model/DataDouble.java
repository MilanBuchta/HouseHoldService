package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@JsonTypeName("double")
public class DataDouble extends AbstractData<Double> {

    @Column(name = "double_value")
    private Double value;

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public void convertValue(String input) {
        setValue(Double.valueOf(input));
    }
}
