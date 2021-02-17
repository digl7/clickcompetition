package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class RegionController {
    @Autowired
    private RegionRepository regionRepository;

    @GetMapping(value = "/region")
    public ResponseEntity<Object> regionList(){
        return new ResponseEntity<>(regionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/region/{id}")
    public ResponseEntity<Object> regionDetail(@PathVariable("id")Long id) {
        return new ResponseEntity<>(regionRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                HttpStatus.OK);
    }

}
