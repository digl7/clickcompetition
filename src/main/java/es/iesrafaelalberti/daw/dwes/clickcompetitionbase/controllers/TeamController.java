package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping(value = "/team")
    public ResponseEntity<Object> teamList(){
        return new ResponseEntity<>(teamRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/team/{id}")
    public ResponseEntity<Object> teamDetail(@PathVariable("id")Long id) {
        return new ResponseEntity<>(teamRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                HttpStatus.OK);
    }

}
