package com.example.springmappingexar.repos;

import com.example.springmappingexar.models.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GameRepository extends JpaRepository<Game,Long> {
    Game findByTitle(String title);

}
