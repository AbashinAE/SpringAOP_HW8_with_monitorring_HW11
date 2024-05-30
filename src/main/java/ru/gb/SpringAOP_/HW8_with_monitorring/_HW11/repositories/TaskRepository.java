package ru.gb.SpringAOP_.HW8_with_monitorring._HW11.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.gb.SpringAOP_.HW8_with_monitorring._HW11.model.Task;
import ru.gb.SpringAOP_.HW8_with_monitorring._HW11.model.TaskStatus;


public interface TaskRepository  extends JpaRepository<Task, Long>{
    List<Task> findByStatus(TaskStatus status);
}