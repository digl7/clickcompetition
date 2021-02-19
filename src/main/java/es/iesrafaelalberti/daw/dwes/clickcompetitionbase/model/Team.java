package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int clicks;


    @ManyToMany
    @JoinTable(
            name = "player_team",
            joinColumns = { @JoinColumn(name = "team_id") },
            inverseJoinColumns = { @JoinColumn(name = "player_id") }
    )

    private List<Player> players;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public Team(Team oldTeam, Long clicks){
        this.name= oldTeam.getName();
        this.id = oldTeam.getId();
        this.players = oldTeam.getPlayers();
        this.clicks = clicks.intValue();
    }

    public Team addPlayers(List<Player> players){
        this.getPlayers().addAll(players);
        return this;
    }

    public void updateClicks() {
        clicks=0;
        for (Player player:this.players
             ) {
            clicks+=player.getClicks();
        }
    }
}
