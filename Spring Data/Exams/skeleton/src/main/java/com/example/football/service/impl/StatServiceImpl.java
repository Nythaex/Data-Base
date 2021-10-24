package com.example.football.service.impl;

import com.example.football.models.dto.seed.xml.stats.SeedStatRoot;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class StatServiceImpl implements StatService {
    private final String PATH="src/main/resources/files/xml/stats.xml";
    private final StatRepository statRepository;
    private final XmlParser parser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public StatServiceImpl(StatRepository statRepository, XmlParser parser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.statRepository = statRepository;
        this.parser = parser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return statRepository.count()>0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {
        SeedStatRoot seedStatRoot = parser.fromFile(PATH, SeedStatRoot.class);

        StringBuilder builder = new StringBuilder();
       seedStatRoot.getStatsList().stream().filter(seedStatDto -> {
            Boolean isValid = validationUtil.isValid(seedStatDto) &&
                    !statRepository.existsByEnduranceAndPassingAndShooting(seedStatDto.getEndurance(),seedStatDto.getPassing(),seedStatDto.getShooting());

            if (isValid) {
                builder.append(String.format("Successfully imported Stat %.2f - %.2f - %.2f%n", seedStatDto.getShooting(),seedStatDto.getPassing(),seedStatDto.getEndurance()));
                return true;
            } else {
                builder.append("Invalid Stat");
                builder.append(System.lineSeparator());
                return false;
            }
        }).forEach(statDto -> {
           Stat stat = modelMapper.map(statDto, Stat.class);
           statRepository.save(stat);

        });
        return builder.toString().trim();
    }

    @Override
    public Stat getStatById(Long id) {
        return statRepository.findById(id).orElse(null);
    }
}
