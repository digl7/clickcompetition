package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TeamRepository extends CrudRepository<Team, Long> {
    @Query("Select new es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team(t, sum(p.clicks)) " +
            "from Team t join t.players p " +
            "group by t.name " +
            "order by sum(p.clicks) desc")
        Collection<Team> teamBests();
}