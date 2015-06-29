package be.persgroep.pe.service.domain.embeddable.grid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
@ApiModel("GridRow")
public class GridRow {
    @NotNull
    @Min(0)
    private Integer tableRowId;

    @NotNull
    @Min(0)
    private Integer tableColumnId;

    @NotNull
    private String value;

    public GridRow() {
    }

    public GridRow(Integer tableRowId, Integer tableColumnId, String value ) {
        this.tableRowId = tableRowId;
        this.tableColumnId = tableColumnId;
        this.value = value;
    }

    public Integer getTableColumnId() {
        return tableColumnId;
    }

    public void setTableColumnId(Integer tableColumnId) {
        this.tableColumnId = tableColumnId;
    }

    public Integer getTableRowId() {
        return tableRowId;
    }

    public void setTableRowId(Integer tableRowId) {
        this.tableRowId = tableRowId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
