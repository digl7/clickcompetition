package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int clicks;

    @JsonBackReference
    @ManyToMany(mappedBy = "players")
    private List<Team> teams;

    //Relacion M-1
    @ManyToOne
    @JoinColumn()
    private Location location;

    public Player() {
    }

    public Player(String name, int clicks, Location location) {
        this.name = name;
        this.clicks = clicks;
        this.location = location;
    }

    public void addClicks(int clicks) {
        this.clicks += clicks;
    }
}
