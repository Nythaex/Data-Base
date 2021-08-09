package com.example.exa.repository;

import com.example.exa.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE (SELECT count(p) from Product p JOIN User u ON p.seller.id=u.id WHERE p.buyer IS NOT NULL)>0 order by u.lastName,u.firstName")
    List<User> usersWhoHaveAtLeastItemSold();

    @Query("SELECT u FROM User u WHERE (SELECT count(p) from Product p JOIN User u ON p.seller.id=u.id WHERE p.buyer IS NOT NULL) >0  order by u.productsSold.size DESC,u.lastName")
    List<User> usersWhoHaveAtLeastItemSoldByCount();
}
