package be.persgroep.pe.service.domain.embeddable;

import com.wordnik.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;

@ApiModel("Embeddable")
public abstract class Embeddable {
    @NotNull
    private EmbeddableAlignment alignment;
    @NotNull
    private Integer ranking;

    public EmbeddableAlignment getAlignment() {
        return this.alignment;
    }

    public void setAlignment(final EmbeddableAlignment alignment) {
        this.alignment = alignment;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

}
