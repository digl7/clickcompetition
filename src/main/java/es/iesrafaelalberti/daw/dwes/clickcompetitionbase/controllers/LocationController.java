package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class LocationController {
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping(value = "/location")
    public ResponseEntity<Object> locationList(){
        return new ResponseEntity<>(locationRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/location/{id}")
    public ResponseEntity<Object> locationDetail(@PathVariable("id")Long id) {
        return new ResponseEntity<>(locationRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                HttpStatus.OK);
    }

    @GetMapping(value="/location/bests")
    public ResponseEntity<Object> locationResults(){
        return new ResponseEntity<>(locationRepository.bestLocation(), HttpStatus.OK);
    }

}
