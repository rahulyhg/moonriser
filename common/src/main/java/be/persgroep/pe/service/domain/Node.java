package be.persgroep.pe.service.domain;

import com.wordnik.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.util.Locale;

/**
 * Created by jlust on 2/12/14.
 */
@ApiModel("Node")
public class Node {
    private String description;
    @NotNull
    private Integer id;
    @NotNull
    private Locale locale;
    private Integer parentId;
    private String title;
    private boolean visible;
    private boolean visibleOnSite;

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisibleOnSite() {
        return visibleOnSite;
    }

    public void setVisibleOnSite(boolean visibleOnSite) {
        this.visibleOnSite = visibleOnSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (id != null ? !id.equals(node.id) : node.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", locale=" + locale +
                '}';
    }
}
