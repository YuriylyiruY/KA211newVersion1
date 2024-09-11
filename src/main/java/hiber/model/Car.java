package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcars;

    @Column(name = "model")
    private String modelCar;

    @Column(name = "series")
    private Integer series;

    public Car() {
    }

    public Car(String modelCar, Integer series) {
        this.modelCar = modelCar;
        this.series = series;
    }

    public Long getIdNewCar() {
        return idcars;
    }

    public void setIdNewCar(Long idNewCar) {
        this.idcars = idNewCar;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    @Override
    public String toString() {
        return "Car: " +
                "  idNewCar = " + idcars +
                "  modelCar = " + modelCar +
                "  series = " + series;

    }
}
