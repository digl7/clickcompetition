package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    @Query("Select p from Player p Where p.clicks >= ?1")
    Collection<Player> findPlayerByClicks(Integer clicks);

    @Query("Select p from Player p order by p.clicks desc")
    Collection<Player> bestPlayer();

    Optional<Player> findPlayerById(Long id);

}
