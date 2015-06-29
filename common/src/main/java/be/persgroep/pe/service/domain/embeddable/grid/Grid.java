package be.persgroep.pe.service.domain.embeddable.grid;

import be.persgroep.pe.service.domain.embeddable.Embeddable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
@ApiModel("Grid")
public class Grid extends Embeddable {
    @ApiModelProperty(value = "An ArrayList containing the rows of the Table", required = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
    @NotNull
    @Valid
    private List<GridRow> gridRowArrayList;

    public Grid() {
        this(new ArrayList<GridRow>());
    }

    public Grid(ArrayList<GridRow> gridRowArrayList) {
        this.gridRowArrayList = gridRowArrayList;
    }

    public List<GridRow> getGridRowArrayList() {
        return gridRowArrayList;
    }

    public void setGridRowArrayList(List<GridRow> gridRowArrayList) {
        this.gridRowArrayList = gridRowArrayList;
    }

    public void setRow(final Integer gridRowId, final Integer gridColumnId, final String gridValue) {
        if (gridRowArrayList == null ) {
            gridRowArrayList = new ArrayList<>();
        }
        GridRow gridRow = new GridRow(gridRowId, gridColumnId, gridValue);
        gridRowArrayList.add(gridRow);
    }
}
