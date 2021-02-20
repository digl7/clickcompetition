package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.TeamRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;

@RestController
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/team")

    public ResponseEntity<Object> teamList(){
        Iterable<Team> teams = teamRepository.findAll(); //todos los equipos
        for (Team team: teams
             ) {
            team.updateClicks();
        }
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(value = "/team/{id}")
    public ResponseEntity<Object> teamDetail(@PathVariable("id")Long id) {
        //TODO: poner clicks en /{id} en todos lados.
        Optional<Team> team = teamRepository.findById(id);
        return new ResponseEntity<>(teamRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                HttpStatus.OK);
    }

    @GetMapping(value = "/team/bests")
    public ResponseEntity<Object> teamDetail() {
        return new ResponseEntity<>(teamRepository.teamBests(), HttpStatus.OK);

    }

    @PutMapping(value  = "/teams/images/{id}")
    public ResponseEntity<Object> teamImageUpdate(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws EntityNotFoundException, IOException {
        imageService.imageTeamStore(file,id);
        return new ResponseEntity<>("Team image " + id + " actualizada", HttpStatus.OK);
    }


}
