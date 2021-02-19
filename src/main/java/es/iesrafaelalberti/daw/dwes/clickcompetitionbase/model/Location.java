package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int clicks;


    //Relacion M-1
    @ManyToOne
    @JoinColumn()
    private Region region;

    //Relacion 1-M
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy= "location", cascade = CascadeType.ALL)
    Set<Player> onLocation = new HashSet<>();

    public Location() {
    }

    public Location(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    public Location(Location oldLocation, Long clicks){
        this.name= oldLocation.getName();
        this.id = oldLocation.getId();
        this.region = oldLocation.getRegion();
        this.clicks = clicks.intValue();
    }

}
