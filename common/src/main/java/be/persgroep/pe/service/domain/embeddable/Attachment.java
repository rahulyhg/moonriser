package be.persgroep.pe.service.domain.embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("Attachment")
public class Attachment extends Embeddable {
    @NotNull
    @ApiModelProperty(value = "The type of the Attachment", required = true)
    private AttachmentType attachmentType;

    @NotNull
    @Length(max=200)
    @ApiModelProperty(value = "The name of the file", required = true)
    private String fileName;

    @NotNull
    @Length(max=10)
    @ApiModelProperty(value = "The extension of the file", required = true)
    private String fileExtension;

    @NotNull
    @ApiModelProperty(value = "The size of the file", required = true)
    private Integer fileSize;

    @NotNull
    @ApiModelProperty(value = "The id of the User", required = true)
    private Integer userId;

    @NotNull
    @ApiModelProperty(value = "The creation date", required = true)
    private DateTime creationDate;

    public AttachmentType getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "attachmentType=" + attachmentType +
                ", fileName='" + fileName + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileSize=" + fileSize +
                ", userId=" + userId +
                ", creationDate=" + creationDate +
                '}';
    }

    public enum AttachmentType {
        PDF (1),
        MPEG (2),
        WAV (3),
        MS_WORD (4),
        OPEN_XML_WORD (5),
        MS_EXCEL (6),
        OPEN_XML_SPREADSHEET (7),
        MS_POWERPOINT (8),
        OPEN_XML_PRESENTATION (9),
        JPG (10),
        GIF (11),
        PNG (12);

        private final Integer attachmentTypeId;

        AttachmentType(Integer attachmentTypeId) {
            this.attachmentTypeId = attachmentTypeId;
        }

        public int getAttachmentTypeId() {
            return this.attachmentTypeId;
        }

    }

}
