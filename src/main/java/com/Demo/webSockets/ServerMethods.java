package com.Demo.webSockets;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.Demo.model.File;

public class ServerMethods {
        private RestTemplate restTemplate;

        ServerMethods(){
            this.restTemplate = new RestTemplate();
        }
         public void getFile(int id){
        final String uri = "http://localhost:8080/Real-Time-Collaborative-Editing/files/id=" + id; // Change URL accordingly
        File file = restTemplate.getForObject(uri, File.class);
       System.out.println("File content: " + file.getContent());
    }

    public void updateContent(int fileID, String newContent) {
        final String uri = "http://localhost:8080/Real-Time-Collaborative-Editing/files/id=" + fileID + "&newContent=" + newContent;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);

    }
}
