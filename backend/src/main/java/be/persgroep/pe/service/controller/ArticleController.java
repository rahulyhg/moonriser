package be.persgroep.pe.service.controller;

import be.persgroep.pe.service.domain.component.ComponentEmoVotesState;
import be.persgroep.pe.service.domain.component.ComponentRatingState;
import be.persgroep.pe.service.domain.component.ComponentReactionState;
import be.persgroep.pe.service.domain.component.ComponentState;
import be.persgroep.pe.service.domain.component.article.Article;
import be.persgroep.pe.service.domain.component.article.ArticleCultureType;
import be.persgroep.pe.service.domain.component.article.ArticleLabelType;
import be.persgroep.pe.service.domain.component.article.ArticleType;
import be.persgroep.pe.service.domain.container.MediaContainer;
import be.persgroep.pe.service.domain.embeddable.EmbeddableAlignment;
import be.persgroep.pe.service.domain.embeddable.FloatingText;
import be.persgroep.pe.service.domain.wrapper.PostArticleRequest;
import be.persgroep.pe.service.domain.wrapper.SaveArticleResponse;
import be.persgroep.pe.service.service.ArticleService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Locale;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Api(value = "articles", description = "Article API")
@RestController
@RequestMapping("/articles")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/id/{originatingKey}", method = RequestMethod.GET)
    public Integer getIdByOriginatingKey(@PathVariable final String originatingKey) {
        Integer articleId = articleService.getIdByOriginatingKey(originatingKey);
        return articleId;
    }

    @ApiOperation(value = "Save Article and return its ID in PE", notes = "Creates a new Article or updates an existing one, based on the originatingKey. " +
            "If an article with the same originatingKey is found, it is updated and the http status code will be 200. " +
            "Otherwise, a new article is created and the http status will be 201. ")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<SaveArticleResponse> saveArticle(@Valid @RequestBody final PostArticleRequest postArticleRequest) {
        logger.info("Create article {} with master node {}, nodes {} and navigations {}",
                postArticleRequest.getArticle(), postArticleRequest.getMasterNode(), postArticleRequest.getNodeIds(), postArticleRequest.getNavigationIds());

        final SaveArticleResponse saveArticleResponse = new SaveArticleResponse();

        Integer articleId = articleService.saveArticle(postArticleRequest.getArticle(), postArticleRequest.getMasterNode(), postArticleRequest.getNodeIds(), postArticleRequest.getNavigationIds());
        saveArticleResponse.setArticleId(articleId);

        return new ResponseEntity<>(saveArticleResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Article in PE", notes = "Deletes an existing Article based on the originatingKey. ")
    @RequestMapping(value = "/{originatingKey}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable final String originatingKey) {
        logger.info("executing archiveComponent article with originatingKey {}", originatingKey);


        articleService.deleteArticle(originatingKey);
    }

    @RequestMapping(value = "/attachments", method = RequestMethod.POST, consumes = "multipart/form-data")
    public Boolean saveAttachment(@RequestParam("file") MultipartFile multipartFile, @RequestParam("destinationFileName") String destinationFileName) {
        try {
            System.out.println("file getOriginalFilename = " + multipartFile.getOriginalFilename());
            System.out.println("file getBytes.length = " + multipartFile.getBytes().length);
            System.out.println("file getContentType = " + multipartFile.getContentType());
            System.out.println("destinationFileName = " + destinationFileName);

            File destinationFile = new File(destinationFileName);

            InputStream inputStream = multipartFile.getInputStream();
            Files.copy(inputStream, destinationFile.toPath(), REPLACE_EXISTING);

        } catch (Exception e) {
            logger.error("FATAL ERROR>>> Attachment with Filename {} exception = ", multipartFile.getOriginalFilename(), e);
        }
        return true;
    }

}
