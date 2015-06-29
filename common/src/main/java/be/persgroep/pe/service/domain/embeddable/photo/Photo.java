package be.persgroep.pe.service.domain.embeddable.photo;

import be.persgroep.pe.service.domain.embeddable.Embeddable;
import be.persgroep.pe.service.domain.embeddable.MimeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class Photo extends Embeddable {
    private Integer dioContentAssetId;
    private List<PhotoCropping> photoCroppings;

    private Integer id;
    private MimeType mimeType;
    private Integer width;
    private Integer height;
    private DateTime originalCreationDate;
    private String caption;
    private String credit;

    public Photo() {
        this.photoCroppings = new ArrayList<>();
    }

    public Integer getDioContentAssetId() {
        return this.dioContentAssetId;
    }

    public void setDioContentAssetId(final Integer dioContentAssetId) {
        this.dioContentAssetId = dioContentAssetId;
    }

    public List<PhotoCropping> getPhotoCroppings() {
        return this.photoCroppings;
    }

    public void addPhotoCropping(final PhotoCropping photoCropping) {
        this.photoCroppings.add(photoCropping);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public MimeType getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(final MimeType mimeType) {
        this.mimeType = mimeType;
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

    public DateTime getOriginalCreationDate() {
        return this.originalCreationDate;
    }

    public void setOriginalCreationDate(final DateTime originalCreationDate) {
        this.originalCreationDate = originalCreationDate;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getCredit() {
        return this.credit;
    }

    public void setCredit(final String credit) {
        this.credit = credit;
    }
}
