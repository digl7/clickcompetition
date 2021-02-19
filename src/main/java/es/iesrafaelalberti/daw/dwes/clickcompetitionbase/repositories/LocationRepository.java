package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Collection;

public interface LocationRepository extends CrudRepository<Location, Long> {
    @Query("Select new es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Location(l, sum(p.clicks)) " +
            "from Location l, Player p " +
            "where l.name=p.location.name " +
            "group by l.name " +
            "order by sum(p.clicks) desc")
      Collection<Object> bestLocation();
}