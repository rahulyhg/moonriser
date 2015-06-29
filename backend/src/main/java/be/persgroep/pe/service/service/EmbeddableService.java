package be.persgroep.pe.service.service;

import be.persgroep.pe.service.data.dao.component.ComponentDao;
import be.persgroep.pe.service.data.dao.embeddable.*;
import be.persgroep.pe.service.domain.embeddable.Embeddable;
import be.persgroep.pe.service.domain.embeddable.EmbeddedComponent;
import be.persgroep.pe.service.domain.embeddable.FloatingText;
import be.persgroep.pe.service.domain.embeddable.freehtml.FreeHTML;
import be.persgroep.pe.service.domain.embeddable.grid.Grid;
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.Quote;
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.SpotifyUri;
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.Tweet;
import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.TwitterWidget;
import be.persgroep.pe.service.domain.embeddable.photo.Photo;
import be.persgroep.pe.service.exception.functional.ComponentNotFoundException;
import be.persgroep.pe.service.exception.functional.UnknownEmbeddableTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmbeddableService {
    @Autowired
    private TweetDao tweetDao;
    @Autowired
    private SpotifyDao spotifyDao;
    @Autowired
    private QuoteDao quoteDao;
    @Autowired
    private GridDao gridDao;
    @Autowired
    private TwitterWidgetDao twitterWidgetDao;
    //@Autowired
    //private AttachmentDao attachmentDao;
    @Autowired
    private ComponentDao componentDao;
    @Autowired
    private AttachmentDao attachmentDao;
    @Autowired
    private FloatingTextDao floatingTextDao;
    @Autowired
    private EmbeddableComponentDao embeddableComponentDao;
    @Autowired
    private FreeHTMLDao freeHTMLDao;
    @Autowired
    private PhotoDao photoDao;

    public void saveEmbeddable(final Quote quote, final int containerId) {
        this.quoteDao.insertQuote(quote, containerId);
    }

    public void saveEmbeddable(final Tweet tweet, final int containerId) {
        this.tweetDao.insertTweet(tweet, containerId);
    }

    public void saveEmbeddable(final SpotifyUri spotifyUri, final int containerId) {
        this.spotifyDao.insertSpotifyUri(spotifyUri, containerId);
    }

    public void saveEmbeddable(final TwitterWidget twitterWidget, final int containerId) {
        this.twitterWidgetDao.insertTwitterWidget(twitterWidget, containerId);
    }

    public void saveEmbeddable(final Grid grid, final int containerId) {
        this.gridDao.insertGrid(grid, containerId);
    }

    public void saveEmbeddable(final EmbeddedComponent embeddedComponent, final int containerId) {
        if (!this.componentDao.existsComponent(embeddedComponent.getComponentId())) {
            throw new ComponentNotFoundException(embeddedComponent.getComponentId());
        }

        this.embeddableComponentDao.insertEmbeddableComponent(embeddedComponent, containerId);
    }

    public void saveEmbeddable(final FloatingText floatingText, final int containerId) {
        this.floatingTextDao.insertFloating(floatingText, containerId);
    }

    public void saveEmbeddable(final FreeHTML freeHTML, final int containerId) {
        this.freeHTMLDao.insertFreeHTML(freeHTML, containerId);
    }

    public void saveEmbeddable(final Photo photo, final int containerId) {
        //TODO PEPR-1540
        try {
            this.photoDao.insertPhoto(photo, containerId);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getStackTrace());
        }
    }


    //TODO
    //public void saveAttachment(final Attachment attachment, final int containerId) {
    //final int attachmentId = this.attachmentDao.insertAttachment(attachment);
    //this.embeddableDao.insertEmbeddableDetailsGrid(containerId, attachment.getAlignment().getId(), attachment.getRanking(), attachmentId);
    //}

    public void saveEmbeddable(final Embeddable embeddable, final int containerId) {
        if (embeddable instanceof Quote) {
            this.saveEmbeddable((Quote) embeddable, containerId);
        } else if (embeddable instanceof Tweet) {
            this.saveEmbeddable((Tweet) embeddable, containerId);
        } else if (embeddable instanceof SpotifyUri) {
            this.saveEmbeddable((SpotifyUri) embeddable, containerId);
        } else if (embeddable instanceof Grid) {
            this.saveEmbeddable((Grid) embeddable, containerId);
        } else if (embeddable instanceof TwitterWidget) {
            this.saveEmbeddable((TwitterWidget) embeddable, containerId);
        } else if (embeddable instanceof EmbeddedComponent) {
            this.saveEmbeddable((EmbeddedComponent) embeddable, containerId);
        } else if (embeddable instanceof FloatingText) {
            this.saveEmbeddable((FloatingText) embeddable, containerId);
        } else if (embeddable instanceof FreeHTML) {
            this.saveEmbeddable((FreeHTML) embeddable, containerId);
        } else if (embeddable instanceof Photo) {
            this.saveEmbeddable((Photo) embeddable, containerId);
        }
//        else if (embeddable instanceof Attachment) {
//            this.saveEmbeddable((Attachment) embeddable, containerId);
//        }
        else {
            throw new UnknownEmbeddableTypeException(containerId, embeddable.getClass().toString());
        }
    }
}
