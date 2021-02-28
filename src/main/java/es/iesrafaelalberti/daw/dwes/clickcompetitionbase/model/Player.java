package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    private String imageUrl;
    private String username;
    private String password;

    @Column (length = 300)
    private String token;

    @JsonBackReference
    @ManyToMany(mappedBy = "players")
    private List<Team> teams;


    //Relacion M-1
    @ManyToOne
    @JoinColumn()
    private Role role;

    //Relacion M-1
    @ManyToOne
    @JoinColumn()
    private Location location;

    public Player() {
    }

    public Player(String name, int clicks, Location location, String username, String password, Role role) {
        this.name = name;
        this.clicks = clicks;
        this.location = location;
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.role = role;
    }

    public void addClicks(int clicks) {
        this.clicks += clicks;
    }

}
