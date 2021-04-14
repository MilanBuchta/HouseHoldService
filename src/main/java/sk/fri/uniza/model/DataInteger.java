package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Column;
import javax.persistence.Entity;

public class DataInteger extends AbstractData<Integer> {

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
