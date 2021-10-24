package softuni.exam.models.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedTicketsRoot {
    @XmlElement(name = "ticket")
    private List<SeedTicketDto> ticketDtoList;

    public SeedTicketsRoot(List<SeedTicketDto> ticketDtoList) {
        this.ticketDtoList = ticketDtoList;
    }

    public SeedTicketsRoot() {
    }

    public List<SeedTicketDto> getTicketDtoList() {
        return ticketDtoList;
    }

    public void setTicketDtoList(List<SeedTicketDto> ticketDtoList) {
        this.ticketDtoList = ticketDtoList;
    }
}
