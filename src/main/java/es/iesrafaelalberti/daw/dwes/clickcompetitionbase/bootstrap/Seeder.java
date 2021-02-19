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
        Location cordoba = locationRepository.save(new Location("Córdoba",andalucia));

        Location manarola = locationRepository.save(new Location("manarola",boltano));
        Location portofino = locationRepository.save(new Location("portofino",boltano));

        Location fuzhou = locationRepository.save(new Location("Fuzhou",fujian));
        Location putian = locationRepository.save(new Location("Putian",fujian));


        Player espanolo = playerRepository.save(new Player("españolito1", 5, cadiz));
        Player fachita = playerRepository.save(new Player("facha", 1, cordoba));
        Player pode = playerRepository.save(new Player("podemitas", 1, cordoba));
        Player vox = playerRepository.save(new Player("votante", 5, cadiz));

        Player itali1 = playerRepository.save(new Player("maccarrani", 5,manarola));
        Player itali2 = playerRepository.save(new Player("spagetti", 6, portofino));

        Player xin = playerRepository.save(new Player("corona", 7,fuzhou));
        Player zao = playerRepository.save(new Player("virus", 8, putian));

        Team chiano = teamRepository.save(new Team("Chinos e Italianos").addPlayers(Arrays.asList(itali1,itali2,xin,zao)));
        Team decadi = teamRepository.save(new Team("Los de cádiz").addPlayers(Arrays.asList(vox, espanolo)));
        Team itali = teamRepository.save(new Team("Italianos").addPlayers(Arrays.asList(itali1,itali2)));
        Team xino = teamRepository.save(new Team("Chinos").addPlayers(Arrays.asList(xin,zao)));
        Team dorcoba = teamRepository.save(new Team("cordobeze").addPlayers(Arrays.asList(fachita,pode)));



    }
}
