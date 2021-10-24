package com.example.exa.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedAllUsersDto {
    @XmlElement(name = "user")
    private List<SeedUserDto> usersDto;

    public SeedAllUsersDto(List<SeedUserDto> usersDto) {
        this.usersDto = usersDto;
    }
    public SeedAllUsersDto() {
    }

    public List<SeedUserDto> getUsersDto() {
        return usersDto;
    }

    public void setUsersDto(List<SeedUserDto> usersDto) {
        this.usersDto = usersDto;
    }

}
