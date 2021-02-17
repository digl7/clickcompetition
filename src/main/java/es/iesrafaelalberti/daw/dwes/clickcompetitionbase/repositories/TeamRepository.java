package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Collection<Team> findPlayerByClicks(Integer clicks);
}
