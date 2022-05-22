package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

public interface DateService {
    List<LocalDateTime> findWeek();
    List<LocalDateTime> findMonth();
    List<LocalDateTime> findYear();
}
