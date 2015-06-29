package be.persgroep.pe.service.domain.embeddable.photo;

public class PhotoCropping {
    private PhotoCroppingType type;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

    public PhotoCroppingType getType() {
        return this.type;
    }

    public void setType(final PhotoCroppingType type) {
        this.type = type;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(final Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(final Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(final Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }
}
