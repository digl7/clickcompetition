package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int clicks;

    //Relacion 1-M
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy= "country", cascade = CascadeType.ALL)
    Set<Region> onCountry = new HashSet<>();

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, int clicks) {
        this.name = name;
        this.clicks = clicks;
    }

    public void addClicks(int clicks) {
        this.clicks += clicks;
    }
}
