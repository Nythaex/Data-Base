package com.example.exa.model.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedAllCategoriesDto {

    @XmlElement(name = "category")
    private List<SeedCategoryDto> categories;

    public SeedAllCategoriesDto(List<SeedCategoryDto> categories) {
        this.categories = categories;
    }

    public SeedAllCategoriesDto() {
    }

    public List<SeedCategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<SeedCategoryDto> categories) {
        this.categories = categories;
    }
}
