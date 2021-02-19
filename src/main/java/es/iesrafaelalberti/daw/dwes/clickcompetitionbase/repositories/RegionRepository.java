package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RegionRepository extends CrudRepository<Region, Long> {
    @Query("Select new es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Region(r, sum(p.clicks)) " +
            "from Region r, Location l, Player p " +
            "where l.name = p.location.name and r.name = l.region.name " +
            "group by r.name order by sum(p.clicks) desc ")
        Collection<Region> regionBests();
}
