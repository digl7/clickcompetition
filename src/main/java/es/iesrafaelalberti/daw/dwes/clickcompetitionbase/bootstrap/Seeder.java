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
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        Country espana = countryRepository.save(new Country("España"));
        Country italia = countryRepository.save(new Country("Italia"));
        Country china = countryRepository.save(new Country("China"));

        Region andalucia = regionRepository.save(new Region("Andalucía", espana));
        Region boltano = regionRepository.save(new Region("Boltano", italia));
        Region fujian = regionRepository.save(new Region("Fujian", china));

        Location cadiz = locationRepository.save(new Location("Cádiz",andalucia));
        Location cordoba = locationRepository.save(new Location("Córdoba",andalucia));

        Location manarola = locationRepository.save(new Location("manarola",boltano));
        Location portofino = locationRepository.save(new Location("portofino",boltano));

        Location fuzhou = locationRepository.save(new Location("Fuzhou",fujian));
        Location putian = locationRepository.save(new Location("Putian",fujian));


        //roles
        Role r1 = roleRepository.save(new Role("ROLE_ADMIN"));
        Role r2 = roleRepository.save(new Role("ROLE_PLAYER"));
        Role r3 = roleRepository.save(new Role("ROLE_GOD"));


        Player espanolo = playerRepository.save(new Player("españolito1", 5, cadiz, "espana", "espana", r1));
        Player fachita = playerRepository.save(new Player("facha", 1, cordoba, "facha", "facha", r2));
        Player pode = playerRepository.save(new Player("podemitas", 1, cordoba, "pode", "pode", r1));
        Player vox = playerRepository.save(new Player("votante", 5, cadiz, "vox","vox",r3));

        Player itali1 = playerRepository.save(new Player("maccarrani", 5,manarola,"macca","macca",r1));
        Player itali2 = playerRepository.save(new Player("spagetti", 6, portofino,"spage","spage",r1));

        Player xin = playerRepository.save(new Player("corona", 7,fuzhou,"corona","corona",r2));
        Player zao = playerRepository.save(new Player("virus", 8, putian,"virus","virus",r1));

        Team chiano = teamRepository.save(new Team("Chinos e Italianos").addPlayers(Arrays.asList(itali1,itali2,xin,zao)));
        Team decadi = teamRepository.save(new Team("Los de cádiz").addPlayers(Arrays.asList(vox, espanolo)));
        Team itali = teamRepository.save(new Team("Italianos").addPlayers(Arrays.asList(itali1,itali2)));
        Team xino = teamRepository.save(new Team("Chinos").addPlayers(Arrays.asList(xin,zao)));
        Team dorcoba = teamRepository.save(new Team("cordobeze").addPlayers(Arrays.asList(fachita,pode)));

    }
}
