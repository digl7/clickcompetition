package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/players")
    public ResponseEntity<Object> playerList(){
        return new ResponseEntity<>(playerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/players/{id}")
    public ResponseEntity<Object> playerDetail(@PathVariable("id") Long id) {

        //TODO: poner clicks en /{id} en todos lados.
        return new ResponseEntity<>(playerRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                                    HttpStatus.OK);
    }

    @GetMapping(value = "/players/morethan/{clicks}")
    public ResponseEntity<Object> bestPlayers(@PathVariable("clicks") Integer clicks) {
        return new ResponseEntity<>(playerRepository.findPlayerByClicks(clicks), HttpStatus.OK);
    }

    @GetMapping(value ="/players/bests")
    public ResponseEntity<Object> playerBests() {
        return new ResponseEntity<>(playerRepository.bestPlayer(),HttpStatus.OK);
    }

    @PutMapping(value  = "/players/images/{id}")
    public ResponseEntity<Object> playerImageUpdate(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws EntityNotFoundException, IOException {
        imageService.imagePlayerStore(file,id);
        return new ResponseEntity<>("Player image " + id + " actualizada", HttpStatus.OK);
    }
}
