package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RegionRepository extends CrudRepository<Region, Long> {
    @Query("Select r.name, r.id, sum(p.clicks) from Region r, Location l, Player p " +
            "where l.name = p.location.name and r.name = l.region.name " +
            "group by r.name , p.clicks order by p.clicks desc ")
        Collection<Object> regionBests();
}
