package com.example.springmappingexar.confs;

import com.example.springmappingexar.models.entity.Game;
import com.example.springmappingexar.models.dtos.AddGameDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper mapper(){
        ModelMapper mapper=new ModelMapper();
        mapper.addMappings(new PropertyMap<AddGameDto, Game>() {
            @Override
            protected void configure() {
                map().setImageThumbnail(source.getThumbnailURL());
            }
        });
        return mapper;
    }


}
