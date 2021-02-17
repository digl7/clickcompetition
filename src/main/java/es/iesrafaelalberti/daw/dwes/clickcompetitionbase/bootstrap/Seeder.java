package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.bootstrap;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.*;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;


@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private TeamRepository teamRepository;


    @Override
    public void run(String... args) throws Exception {

        Country espana = countryRepository.save(new Country("España"));
        Country italia = countryRepository.save(new Country("Italia"));
        Country china = countryRepository.save(new Country("China"));

        Region andalucia = regionRepository.save(new Region("Andalucía", espana));
        Region boltano = regionRepository.save(new Region("Boltano", italia));
        Region fujian = regionRepository.save(new Region("fujian", china));

        Location cadiz = locationRepository.save(new Location("Cádiz",andalucia));
        Location malaga = locationRepository.save(new Location("Cádiz",andalucia));
        Location cordoba = locationRepository.save(new Location("Cádiz",andalucia));

        Location manarola = locationRepository.save(new Location("manarola",boltano));
        Location portofino = locationRepository.save(new Location("portofino",boltano));

        Location fuzhou = locationRepository.save(new Location("Fuzhou",fujian));
        Location putian = locationRepository.save(new Location("Putian",fujian));

        Team chiano = teamRepository.save(new Team("Chinos e Italianos"));
        Team decadi = teamRepository.save(new Team("Los de cádiz"));
        Team itali = teamRepository.save(new Team("Italianos"));
        Team xino = teamRepository.save(new Team("Chinos"));

        Player espanolo = playerRepository.save(new Player("españolito1", 0, cadiz));

        Player fachita = playerRepository.save(new Player("facha", 10, malaga));
        Team prueba = teamRepository.save(new Team("prueba"));
        prueba.addPlayers(Arrays.asList(espanolo,fachita));

        playerRepository.save(new Player("podemitas", 22, cordoba));
        playerRepository.save(new Player("votante", 7, cadiz));
        playerRepository.save(new Player("maccarrani", 13,manarola));
        playerRepository.save(new Player("spagetti", 2, portofino));

        playerRepository.save(new Player("corona", 13,fuzhou));
        playerRepository.save(new Player("virus", 2, putian));





    }
}
