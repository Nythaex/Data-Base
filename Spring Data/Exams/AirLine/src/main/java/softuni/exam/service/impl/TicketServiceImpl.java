package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.xml.SeedTicketsRoot;
import softuni.exam.models.entities.Ticket;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TicketServiceImpl implements TicketService {
    private final String PATH = "src/main/resources/files/xml/tickets.xml";
    private final TicketRepository ticketRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownService townService;
    private final PlaneService planeService;
    private final PassengerService passengerService;

    public TicketServiceImpl(TicketRepository ticketRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, TownService townService, PlaneService planeService, PassengerService passengerService) {
        this.ticketRepository = ticketRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townService = townService;
        this.planeService = planeService;
        this.passengerService = passengerService;
    }

    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {
        SeedTicketsRoot seedTicketsRoot = xmlParser.fromFile(PATH, SeedTicketsRoot.class);

        StringBuilder builder = new StringBuilder();
        seedTicketsRoot.getTicketDtoList().stream().filter(seedTicketDto -> {
            Boolean isValid = validationUtil.isValid(seedTicketDto) &&
                    !ticketRepository.existsBySerialNumber(seedTicketDto.getSerialNumber());
            if (isValid) {
                builder.append(String.format("Successfully imported Ticket %s - %s%n", seedTicketDto.getFromTown().getName(), seedTicketDto.getToTown().getName()));
                return true;
            } else {
                builder.append("Invalid Ticket");
                builder.append(System.lineSeparator());
                return false;
            }
        }).forEach(ticketDto -> {
            Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
            ticket.setFromTown(townService.getTownByName(ticket.getFromTown().getName()));
            ticket.setToTown(townService.getTownByName(ticket.getToTown().getName()));
            ticket.setPassenger(passengerService.getPassengerByEmail(ticket.getPassenger().getEmail()));
            ticket.setPlane(planeService.getPlaneByRegisterNumber(ticket.getPlane().getRegisterNumber()));

            ticketRepository.save(ticket);
        });
        return builder.toString().trim();
    }
}
