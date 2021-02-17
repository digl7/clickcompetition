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


    @JsonBackReference
    @ManyToMany(cascade = { CascadeType.ALL })
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

    public Team addPlayers(List<Player> players){
        this.getPlayers().addAll(players);
        return this;
    }

    public void addClicks(int clicks) {
        this.clicks += clicks;
    }
}
