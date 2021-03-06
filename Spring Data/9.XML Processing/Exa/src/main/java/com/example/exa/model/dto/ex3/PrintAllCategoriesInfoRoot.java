package com.example.exa.model.dto.ex3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintAllCategoriesInfoRoot {

    @XmlElement(name = "category")
    private List<CategoryDto> categories;

    public PrintAllCategoriesInfoRoot(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public
    PrintAllCategoriesInfoRoot() {
    };
}
