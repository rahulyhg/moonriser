package be.persgroep.pe.service.extservice;

import be.persgroep.pe.service.domain.embeddable.MimeType;
import be.persgroep.pe.service.domain.embeddable.photo.Photo;
import be.persgroep.pe.service.exception.functional.UnknownMimeTypeException;
import be.persgroep.red.diocontent.api.asset.Asset;
import be.persgroep.red.diocontent.api.asset.ImageInfo;
import be.persgroep.red.diocontent.api.attachment.Attachment;
import be.persgroep.red.diocontent.api.attachment.AttachmentRole;
import be.persgroep.red.diocontent.api.attachment.ImageFileInfo;
import be.persgroep.red.diocontent.api.client.DioContentClient;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DioContentStub {
    @Autowired
    private DioContentClient dioContentClient;

    public void fillInPhotoInfo(final Photo photo) {
        final Asset asset = this.dioContentClient.getAsset(photo.getDioContentAssetId());
        final ImageInfo imageInfo = (ImageInfo) asset.getAdditionalInfo();
        final Attachment attachment = this.dioContentClient.getAttachmentWithRole(asset.getId(), AttachmentRole.ORIGINAL);
        final ImageFileInfo imageFileInfo = (ImageFileInfo) attachment.getFileInfo();

        photo.setCaption(imageInfo.getCaption());
        photo.setOriginalCreationDate(new DateTime(asset.getCreateDate()));

        photo.setHeight(imageFileInfo.getPixelHeight());
        photo.setWidth(imageFileInfo.getPixelWidth());

        photo.setMimeType(MimeType.getMimeType(imageFileInfo.getMimeType().toLowerCase()));
        if (photo.getMimeType() == null) {
            throw new UnknownMimeTypeException(photo.getDioContentAssetId(), imageFileInfo.getMimeType().toLowerCase());
        }

        photo.setCredit(imageInfo.getPhotographer());
        if (imageInfo.getCredit() != null && !imageInfo.getCredit().isEmpty()) {
            photo.setCredit(imageInfo.getCredit());
        }
    }

    public void saveAssetAttachmentToLocation(final int assetId, final File file) throws IOException {
        Files.createDirectories(Paths.get(file.getParent()));
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);

        this.dioContentClient.downloadAttachmentWithRole(assetId, AttachmentRole.ORIGINAL, fileOutputStream);

        fileOutputStream.close();
    }
}
