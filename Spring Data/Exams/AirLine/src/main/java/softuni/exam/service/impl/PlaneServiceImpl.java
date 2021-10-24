package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.json.SeedTownDto;
import softuni.exam.models.dtos.xml.SeedPlaneRoot;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final String PATH = "src/main/resources/files/xml/planes.xml";
    private final PlaneRepository planeRepository;
    private  XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PlaneServiceImpl(PlaneRepository planeRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importPlanes() throws JAXBException, FileNotFoundException {
        SeedPlaneRoot seedPlaneRoot = xmlParser.fromFile(PATH, SeedPlaneRoot.class);

        StringBuilder builder = new StringBuilder();
        seedPlaneRoot.getPlanes().stream().filter(seedPlaneDto -> {
            Boolean isValid = validationUtil.isValid(seedPlaneDto) &&
                    !planeRepository.existsByRegisterNumber(seedPlaneDto.getRegisterNumber());
            if (isValid) {
                builder.append(String.format("Successfully imported Plane %s%n", seedPlaneDto.getRegisterNumber()));
                return true;
            } else {
                builder.append("Invalid Plane");
                builder.append(System.lineSeparator());
                return false;
            }
        }).forEach(planeDto -> {
            Plane plane = modelMapper.map(planeDto, Plane.class);
            planeRepository.save(plane);

        });
        return builder.toString().trim();
    }

    @Override
    public Plane getPlaneByRegisterNumber(String registerNumber) {
        return planeRepository.findByRegisterNumber(registerNumber);
    }
}
