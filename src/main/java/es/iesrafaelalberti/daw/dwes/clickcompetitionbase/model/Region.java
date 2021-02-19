package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int clicks;

    //Relacion M-1
    @ManyToOne
    @JoinColumn()
    private Country country;

    //Relacion 1-M
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy= "region", cascade = CascadeType.ALL)
    Set<Location> onRegion = new HashSet<>();

    public Region() {
    }

    public Region(String name, Country country) {
        this.name = name;
        this.country = country;
    }
    public Region(Region oldRegion, Long clicks){
        this.name= oldRegion.getName();
        this.id = oldRegion.getId();
        this.country = oldRegion.getCountry();
        this.clicks = clicks.intValue();
    }
}
