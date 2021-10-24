package softuni.exam.models.dtos.seed.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class SeedCarDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private int kilometers;
    @Expose
    private String registeredOn;

    public SeedCarDto(String make, String model, int kilometers, String registeredOn) {
        this.make = make;
        this.model = model;
        this.kilometers = kilometers;
        this.registeredOn = registeredOn;
    }

    public SeedCarDto() {
    }

    @Size(min = 3 ,max = 19)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    @Size(min = 3 ,max = 19)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Positive
    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }
}
