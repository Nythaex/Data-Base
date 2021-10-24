package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PlayerRepository  extends JpaRepository<Player,Long> {


    boolean existsByEmail(String email);


    @Query("SELECT p FROM Player p WHERE p.birthDate>:after AND p.birthDate<:before ORDER BY p.stat.shooting desc,p.stat.passing DESC,p.stat.endurance DESC,p.lastName ASC")
    List<Player> getBestPlayers(@Param("after") LocalDate after,
                                @Param("before") LocalDate before);
}
