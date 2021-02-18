package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TeamRepository extends CrudRepository<Team, Long> {
    @Query("Select p from Team_player pl,  Team t, Player p Where p.name = t.name")
        Collection<Object> showTeams();
}
