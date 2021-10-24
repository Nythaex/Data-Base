package softuni.exam.models.dtos.seed.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class SeedPictureDto {
    @Expose
    private String name;
    @Expose
    private String dateAndTime;
    @Expose
    private Long car;

    public SeedPictureDto(String name, String dateAndTime, Long car) {
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.car = car;
    }

    public SeedPictureDto() {
    }

    @Size(min = 3,max = 19)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }
}
