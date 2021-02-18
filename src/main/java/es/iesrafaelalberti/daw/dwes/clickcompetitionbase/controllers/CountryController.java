package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping(value = "/country")
    public ResponseEntity<Object> countryList(){
        return new ResponseEntity<>(countryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/country/{id}")
    public ResponseEntity<Object> countryDetail(@PathVariable("id")Long id) {
        return new ResponseEntity<>(countryRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                HttpStatus.OK);
    }

    @GetMapping(value ="/country/bests")
    public ResponseEntity<Object> regionBests() {
        return new ResponseEntity<>(countryRepository.countryBests(),HttpStatus.OK);

    }

}
