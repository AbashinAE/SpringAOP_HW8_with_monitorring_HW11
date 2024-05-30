package ru.gb.SpringAOP_.HW8_with_monitorring._HW11.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskText;
    private TaskStatus status;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
}