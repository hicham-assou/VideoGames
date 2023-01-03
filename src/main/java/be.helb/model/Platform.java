package be.helb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Platform implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "platform", cascade = CascadeType.ALL)
    private List<Game> game;

    public void setGames(List<Game> games) {
        this.game = games;
    }

    @JsonIgnore // boucle a l infini sinn
    public List<Game> getGames() {
        return game;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
