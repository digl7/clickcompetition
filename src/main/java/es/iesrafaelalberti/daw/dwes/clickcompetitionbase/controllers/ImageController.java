package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping(value ="/download/players/{name}")
    public ResponseEntity<UrlResource> getImagePlayer(@PathVariable("name") String name) throws Exception{
        Path targetPath = Paths.get("./images/players/"
                +name)
                .normalize();
        try {
            UrlResource resource = new UrlResource(targetPath.toUri());
            if(resource.exists()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
            }
        } catch (MalformedURLException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value ="/download/teams/{name}")
    public ResponseEntity<UrlResource> getImageTeams(@PathVariable("name") String name) throws Exception{
        Path targetPath = Paths.get("./images/teams/"
                +name)
                .normalize();
        try {
            UrlResource resource = new UrlResource(targetPath.toUri());
            if(resource.exists()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
            }
        } catch (MalformedURLException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
