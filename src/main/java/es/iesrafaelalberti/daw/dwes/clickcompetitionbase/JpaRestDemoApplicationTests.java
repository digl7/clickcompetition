package es.iesrafaelalberti.daw.dwes.clickcompetitionbase;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.*;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.*;
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
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void arrageTest() {

    }

    @Test
    void insertPlayer() {
        Country espana = countryRepository.save(new Country("España"));
        Region andalucia = regionRepository.save(new Region("Andalucía", espana));
        Location cadiz = locationRepository.save(new Location("Cádiz",andalucia));
        Role r3 = roleRepository.save(new Role("ROLE_GOD"));

        Player player1 = playerRepository.save(new Player("Juan", 12, cadiz, "juan", "juan", r3));

        assert playerRepository.findPlayerById(player1.getId()).isPresent();

    }
}