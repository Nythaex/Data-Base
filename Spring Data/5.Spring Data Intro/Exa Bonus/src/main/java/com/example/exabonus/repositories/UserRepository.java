package com.example.exabonus.repositories;

import com.example.exabonus.models.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    List<Users> findAll();

    @Modifying
    @Transactional
    @Query("update Users as u set u.is_deleted=true where :date >u.last_time_logged_in")
    void setIsDeleted(@Param("date") LocalDate localDate);
}
