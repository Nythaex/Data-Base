package com.example.springmappingexar;

import com.example.springmappingexar.models.dtos.GameDetailsDto;
import com.example.springmappingexar.models.dtos.LoginUserDto;
import com.example.springmappingexar.models.dtos.RegisterUserDto;
import com.example.springmappingexar.models.dtos.AddGameDto;
import com.example.springmappingexar.services.interfaces.GameService;
import com.example.springmappingexar.services.interfaces.OrderService;
import com.example.springmappingexar.services.interfaces.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class Runner implements ApplicationRunner {

   private final UserService userService;
   private final GameService gameService;
   private  final OrderService orderService;
   private final BufferedReader reader;

    public Runner(UserService userService, GameService gameService, OrderService orderService) {
        this.gameService = gameService;
        this.orderService = orderService;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        while (true){
            System.out.print("Write command:");
            String [] commands=reader.readLine().split("\\|");
            switch (commands[0]){
                case "RegisterUser":
                    RegisterUserDto registerUserDto=new RegisterUserDto(commands[1],commands[2],commands[3],commands[4]);
                  userService.registerUser(registerUserDto);
                    break;
                case "LoginUser":
                    LoginUserDto loginUserDto=new LoginUserDto(commands[1],commands[2]);
                   userService.login(loginUserDto);
                    break;
                case "Logout":
                     userService.logout();
                    break;
                case "AddGame":
                    AddGameDto addGameDto=new AddGameDto(commands[1], BigDecimal.valueOf(Double.parseDouble(commands[2])),Double.parseDouble(commands[3]),commands[4],commands[5],commands[6],
                            LocalDate.parse(commands[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    gameService.addGame(addGameDto);
                    break;
                case "EditGame":
                     gameService.editGame(Long.parseLong(commands[1]),commands);
                    break;
                case "DeleteGame":
                     gameService.deleteGame(Long.valueOf(commands[1]));
                    break;
                case "AllGames":
                    gameService.getAllGames().forEach(s-> System.out.println(s.getTitle()+" "+s.getPrice()));
                    break;
                case "DetailGame":
                    GameDetailsDto gameDetails = gameService.getGameDetails(commands[1]);
                    System.out.printf("Title: %s%nPrice: %.2f%nDescription: %s%n",gameDetails.getTitle(),Double.parseDouble(String.valueOf(gameDetails.getPrice())),gameDetails.getDescription());
                    System.out.printf("Release date: %s%n",gameDetails.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    break;
                case "OwnedGames":
                    userService.getAllMyGames().forEach(s-> System.out.println(s.getTitle()));
                    break;
                case "AddItem":
                    orderService.addGameToUser(commands[1]);
                    break;
                case "RemoveItem":
                    orderService.removeItem(commands[1]);
                    break;
                case "BuyItem":
                   orderService.buyItems();
                    break;
            }


        }

    }
}
