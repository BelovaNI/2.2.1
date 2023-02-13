package hiber.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;


    @OneToOne (optional=false, mappedBy="car")
    private User user;

    public User getUser() {
        return user;
    }

    public User setUser(User user) {
        this.user = user;
        return user;
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return " " +
                "model " + '\"' + model + '\"' +
                ", series " + series;
    }
}
