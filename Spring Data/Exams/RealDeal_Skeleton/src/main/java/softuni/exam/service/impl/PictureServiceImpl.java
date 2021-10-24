package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.seed.json.SeedPictureDto;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {
    private final String PATH="src/main/resources/files/json/pictures.json";
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final CarService carService;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, CarService carService) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.carService = carService;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count()>0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importPictures() throws IOException {
        SeedPictureDto[] seedPictureDtos = gson.fromJson(readPicturesFromFile(), SeedPictureDto[].class);
        StringBuilder builder=new StringBuilder();

        Arrays.stream(seedPictureDtos).filter(s1->{
            if (validationUtil.isValid(s1)){
                builder.append(String.format("Successfully import picture - %s%n",s1.getName()));
                return true;
            }else {
                builder.append(String.format("Invalid picture%n"));
                return false;
            }
        }).forEach(s1->{
            Picture map = modelMapper.map(s1, Picture.class);
            modelMapper.map(s1, Picture.class);
            map.setCar(carService.getCarById(s1.getCar()));
            pictureRepository.save(map);
        });

        return builder.toString().trim();
    }
}
