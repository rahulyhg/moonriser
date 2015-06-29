package be.persgroep.pe.service.domain.embeddable.photo;


public enum PhotoCroppingType {
    BASE("base"), MEDIA_L("media_l"), MEDIA_XL("media_xl");

    private final String name;

    private PhotoCroppingType(final String name) {
        this.name = name;
    }
}
