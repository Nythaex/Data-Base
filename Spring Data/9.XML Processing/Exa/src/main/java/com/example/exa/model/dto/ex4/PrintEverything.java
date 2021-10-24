package com.example.exa.model.dto.ex4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrintEverything {
    @XmlElement(name = "users")
    private AllUserWitSoldItemsDto everything;

    public PrintEverything(AllUserWitSoldItemsDto everything) {
        this.everything = everything;
    }

    public PrintEverything() {
    }

    public AllUserWitSoldItemsDto getEverything() {
        return everything;
    }

    public void setEverything(AllUserWitSoldItemsDto everything) {
        this.everything = everything;
    }
}

