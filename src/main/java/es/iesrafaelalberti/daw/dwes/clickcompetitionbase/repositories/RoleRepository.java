package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;


import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query("Select r from Role r JOIN FETCH r.player where r.id = :id")
    Role findPlayers(@Param("id") Long id);
}
