package softuni.exam.models.dtos.json;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class SeedTownDto {
    @Expose
    private String name;
    @Expose
    private int population;
    @Expose
    private String guide;

    public SeedTownDto() {

    }

    public SeedTownDto(String name, int population, String guide) {
        this.name = name;
        this.population = population;
        this.guide = guide;
    }

    @Size(min = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Positive
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

}
