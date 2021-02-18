package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Collection;

public interface LocationRepository extends CrudRepository<Location, Long> {
    @Query("Select l.id, l.name, sum(p.clicks) from Location l, Player p where l.name=p.location.name group by l.name order by p.clicks desc")
    Collection<Object> bestLocation();
}
//@Query("Select l.id, l.name, p.clicks from Location l, Player p group by p.clicks order by p.clicks")
//SELECT l.id, l.NAME, sum(p.clicks) from location l, player p WHERE p.id_location=l.id GROUP BY l.NAME  ORDER BY clicks desc;
