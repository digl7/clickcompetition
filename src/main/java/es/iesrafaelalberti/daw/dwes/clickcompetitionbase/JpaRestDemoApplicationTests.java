package es.iesrafaelalberti.daw.dwes.clickcompetitionbase;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Country;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Location;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Region;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.CountryRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.LocationRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaRestDemoApplicationTests {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private LocationRepository locationRepository;
    
    @BeforeEach
    void arrageTest() {

    }
    
    @Test
    void insertPlayer() {
        Country espana = countryRepository.save(new Country("España"));
        Region andalucia = regionRepository.save(new Region("Andalucía", espana));
        Location cadiz = locationRepository.save(new Location("Cádiz",andalucia));

        Player player1 = playerRepository.save(new Player("Juan", 12, cadiz));

        assert playerRepository.findPlayerById(player1.getId()).isPresent();

    }
}