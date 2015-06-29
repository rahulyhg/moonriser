package be.persgroep.pe.service.domain.container;

import be.persgroep.pe.service.domain.embeddable.Embeddable;
import be.persgroep.pe.service.domain.component.ContainerType;
import be.persgroep.pe.service.domain.embeddable.EmbeddableAlignment;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Container {
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
    @Valid
    private List<Embeddable> embeddables;

    private Integer id;
    @Length(max = 4000)
    private String paragraphText;
    @Length(max = 255)
    private String title;
    @NotNull
    private ContainerType type;

    public Container() {
        this(ContainerType.MEDIA);
    }

    public Container(final ContainerType type) {
        this.type = type;
        this.embeddables = new ArrayList<>();
    }

    public void setType(ContainerType type) {
        this.type = type;
    }


    public void addEmbeddable(final Embeddable embeddable) {
        this.embeddables.add(embeddable);
    }

    public void addEmbeddable(final Embeddable embeddable, final EmbeddableAlignment alignment) {
        embeddable.setAlignment(alignment);
        this.embeddables.add(embeddable);
    }

    public List<Embeddable> getEmbeddables() {
        return this.embeddables;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParagraphText() {
        return this.paragraphText;
    }

    public void setParagraphText(final String paragraphText) {
        this.paragraphText = paragraphText;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public ContainerType getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Container container = (Container) o;

        if (embeddables != null ? !embeddables.equals(container.embeddables) : container.embeddables != null)
            return false;
        if (id != null ? !id.equals(container.id) : container.id != null) return false;
        if (paragraphText != null ? !paragraphText.equals(container.paragraphText) : container.paragraphText != null)
            return false;
        if (title != null ? !title.equals(container.title) : container.title != null) return false;
        if (type != container.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = embeddables != null ? embeddables.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (paragraphText != null ? paragraphText.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Container{" +
                "embeddables=" + embeddables +
                ", id=" + id +
                ", paragraphText='" + paragraphText + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }
}
