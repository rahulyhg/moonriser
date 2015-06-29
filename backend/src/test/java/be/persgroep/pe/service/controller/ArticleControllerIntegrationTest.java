//package be.persgroep.pe.service.controller;
//
//import be.persgroep.pe.service.Application;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.IntegrationTest;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.File;
//
//import static org.junit.Assert.assertTrue;
//
///**
// * Created by wim van den brande on 10/12/2014.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
//@IntegrationTest({"server.port:8888", "management.port:-1"})
//public class ArticleControllerIntegrationTest {
//    RestTemplate template = new RestTemplate();
//
//    @Test
//    public void shouldPostFile() {
//        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
//        FileSystemResource fileSystemResource = new FileSystemResource(new File("c:/temp/temp.jpg"));
//
//        multiValueMap.add("file", fileSystemResource);
//        multiValueMap.add("destinationFileName", "c:/temp/copy.jpg");
//
//        Boolean success = template.postForObject("http://localhost:8888/articles/attachments", multiValueMap, Boolean.class);
//        assertTrue(success);
//    }
//
//}
