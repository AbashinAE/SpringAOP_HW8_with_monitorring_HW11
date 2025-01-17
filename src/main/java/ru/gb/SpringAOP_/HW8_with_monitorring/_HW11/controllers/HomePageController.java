package ru.gb.SpringAOP_.HW8_with_monitorring._HW11.controllers;

import java.time.LocalDateTime;
import java.util.List;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.gb.SpringAOP_.HW8_with_monitorring._HW11.model.Task;
import ru.gb.SpringAOP_.HW8_with_monitorring._HW11.model.TaskStatus;
import ru.gb.SpringAOP_.HW8_with_monitorring._HW11.services.DataProcessingService;

/**
 * Класс основной страницы выводит список Task
 */
@RestController
public class HomePageController {
    private final DataProcessingService dataService;

    public HomePageController(DataProcessingService dataService) {
        this.dataService = dataService;
    }

    private final Counter counter = Metrics.counter("get_notes_count");
    @GetMapping("/")
    public List<Task> getAllTasks(){
        counter.increment();
        return dataService.getListTasks();
    }

    @PutMapping("/")
    public Task addTask(@RequestBody Task task) {
        Task currentTask = task;

        var currentTime = LocalDateTime.now(); // время создания и последнего обновления
        currentTask.setCreateTime(currentTime);
        currentTask.setLastUpdateTime(currentTime);

        currentTask.setStatus(TaskStatus.NOT_STARTED);// статус по умолчанию при добавлении
        return dataService.addTask(currentTask);
    }
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status){
        return dataService.getListTasksByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        return dataService.updateTaskStatusWithId(id, status);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        dataService.deleteTaskWithId(id);
    }
}