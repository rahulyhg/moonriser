package be.persgroep.pe.service.domain.embeddable.freehtml;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FreeHTMLTemplateType {
    YOUTUBE(3),
    DAILY_MOTION(4),
    GARAGE_TV(5),
    FREE_HTML(6),
    LIVE_LEAK(7),
    PE(8),
    GOOGLE_MAP(9),
    WAT_TV(10),
    VIDEO_GOOGLE_COM(11),
    BLIP_TV(12),
    VIMEO_COM(13),
    VIDEO_NL(14),
    BRIGHTCOVE_COM(15),
    NOS_NL(16),
    FILMTRAILER_COM(17),
    DE_REDACTIE(20),
    METACAFE(21),
    GAMETRAILERS(22),
    MYSPACE_VIDEO(23),
    COLLEGE_HUMOR(24),
    VMMA(25),
    BELGA_BE(26),
    ZOOMIN_TV(27),
    VIDEOSTRIP_MY_CONTENT(28),
    VIDEOSTRIP_OTHER_CONTENT(29),
    YOUTUBE2(30),
    VIMEO(31),
    DAILY_MOTION2(32),
    BLIP_TV2(33),
    COLLEG_HUMOR(34),
    MUZU(35),
    VIER(36);

    private final int id;

    private FreeHTMLTemplateType(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static final FreeHTMLTemplateType fromValue(final String value) {
        return FreeHTMLTemplateType.valueOf(value);
    }

    public int getId() {
        return this.id;
    }

    @JsonValue
    public final String value() {
        return super.name();
    }

}
