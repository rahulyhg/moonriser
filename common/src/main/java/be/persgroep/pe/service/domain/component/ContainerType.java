package be.persgroep.pe.service.domain.component;

//TODO: Will be replaced
@Deprecated
public enum ContainerType {
    TOP(1), INTRO(2), MEDIA(3), FRAME(4), REVIEW_MIRROR(5);

    private final int id;

    private ContainerType(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
