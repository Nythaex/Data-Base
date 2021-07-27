package com.example.advquerying.repositories;

import com.example.advquerying.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<Class extends BaseEntity> extends JpaRepository<Class,Long> {
}
