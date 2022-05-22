package com.example.demo.repository;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoomsRepository extends JpaRepository<Room,Long> {
}
