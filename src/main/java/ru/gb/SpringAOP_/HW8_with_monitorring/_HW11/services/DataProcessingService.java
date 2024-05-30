package ru.gb.SpringAOP_.HW8_with_monitorring._HW11.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ru.gb.SpringAOP_.HW8_with_monitorring._HW11.aspect.TrackUserAction;
import ru.gb.SpringAOP_.HW8_with_monitorring._HW11.model.Task;
import ru.gb.SpringAOP_.HW8_with_monitorring._HW11.model.TaskStatus;
import ru.gb.SpringAOP_.HW8_with_monitorring._HW11.repositories.TaskRepository;

/**
 * Сервис инкапсулирующий логику работы с базой данных
 */
@Service

public class DataProcessingService {
    private TaskRepository taskRepository;

    public DataProcessingService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @TrackUserAction
    public List<Task> getListTasks() {
        return taskRepository.findAll();
    }

    @TrackUserAction
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @TrackUserAction
    public List<Task> getListTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public Task updateTaskStatusWithId(Long id, TaskStatus status) {
        Task updatedTask = taskRepository.getReferenceById(id);
        updatedTask.setStatus(status);
        updatedTask.setLastUpdateTime(LocalDateTime.now());
        return taskRepository.saveAndFlush(updatedTask);
    }

    @TrackUserAction
    public void deleteTaskWithId(Long id) {
        taskRepository.delete(taskRepository.getReferenceById(id));
    }
}