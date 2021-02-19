package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CountryRepository extends CrudRepository<Country, Long> {
    @Query("Select new es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Country(c, sum(p.clicks)) " +
            "from Country c, Region r, Location l, Player p " +
            "where l.name = p.location.name and r.name = l.region.name and c.name = r.country.name " +
            "group by c.name order by sum(p.clicks) desc ")
    Collection<Country> countryBests();
}
