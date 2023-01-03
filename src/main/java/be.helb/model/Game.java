package be.helb.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "id_user")
    private int id_user;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }



    public Platform getPlatform() {
        return platform;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public int getId_user() {
        return id_user;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }




    public Game() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game(String name){
        this.name = name;
    }

}
