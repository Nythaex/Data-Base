package com.example.springmappingexar.services.impl;

import com.example.springmappingexar.models.entity.Game;
import com.example.springmappingexar.models.entity.Order;
import com.example.springmappingexar.models.entity.User;
import com.example.springmappingexar.repos.GameRepository;
import com.example.springmappingexar.repos.OrderRepository;
import com.example.springmappingexar.repos.UserRepository;
import com.example.springmappingexar.services.interfaces.OrderService;
import com.example.springmappingexar.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private User loggedIn;
    private List<Game> orderCart;

    public OrderServiceImpl(GameRepository gameRepository, UserRepository userRepository, OrderRepository orderRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public void addGameToUser(String title) {
        loggedIn=userService.getLoggedIn();
        orderCart=userService.getOrderCart();

        if (loggedIn == null) {
            System.out.println("Please login");
            return;
        }
        for (Game game : loggedIn.getGames()) {
            if (game.getTitle().equals(title)) {
                System.out.println("Already have it in the account.");
                return;
            }
        }
        for (Game s : orderCart) {
            if (s.getTitle().equals(title)) {
                System.out.println("Already have it in the cart.");
                return;
            }
        }
        System.out.println(title + " added to cart.");
        orderCart.add(gameRepository.findByTitle(title));

    }

    @Override
    public void removeItem(String title) {
        loggedIn=userService.getLoggedIn();
        orderCart=userService.getOrderCart();

        for (Game s : orderCart) {
            if (s.getTitle().equals(title)) {
                orderCart.remove(s);
                System.out.println(title + " removed from cart.");
                return;
            }
        }
        System.out.println("No such a game in the cart.");
    }

    @Override
    public void buyItems() {
        loggedIn=userService.getLoggedIn();
        orderCart=userService.getOrderCart();

        if (orderCart.isEmpty()) {
            System.out.println("Empty cart.");
            return;
        }
        Order order = new Order(loggedIn);
        loggedIn.getOrders().add(order);
        System.out.println("Successfully bought games:");
        orderCart.forEach(s -> {
            order.getGames().add(s);
            loggedIn.getGames().add(s);
            System.out.println(" -" + s.getTitle());
        });
        orderRepository.save(order);
        userRepository.save(loggedIn);
    }
}
