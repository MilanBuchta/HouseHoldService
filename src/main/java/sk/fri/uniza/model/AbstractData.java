package sk.fri.uniza.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

public abstract class AbstractData<T extends Object> {

    private Long id;

    private Field field;

    private HouseHold houseHold;

    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty(example = "20.5", dataType = "double")
    public abstract T getValue();

    public abstract void setValue(T value);

    public abstract void convertValue(String input);

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @ApiModelProperty(hidden = true)
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @ApiModelProperty(hidden = true)
    public HouseHold getHouseHold() {
        return houseHold;
    }

    public void setHouseHold(HouseHold houseHold) {
        this.houseHold = houseHold;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }


    // Táto metóda je tu iba z dôvodu vyriešenia bug-u v Swagger dokumentacii
    // Swagger negeneruje examples v resource spravne preto je to vyriesene
    // takto.
    @ApiModelProperty(name = "type",
            example = "double ALEBO integer ALEBO string",
            value = "double")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getType__() {
        return null;
    }

}
