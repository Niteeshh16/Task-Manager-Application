package com.niteesh.taskservice.service;

import com.niteesh.taskservice.enums.TaskStatus;
import com.niteesh.taskservice.model.Task;
import com.niteesh.taskservice.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplementation implements  TaskService {
    private final TaskRepository taskRepository;


    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task, String requesterRole) throws Exception {
        if (!requesterRole.equals("ROLE_ADMIN")) {
            throw new Exception("Only admins can create tasks.");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(()->new Exception(("task not found with id"+id)));
    }

    @Override
    public List<Task> getAllTasks(TaskStatus status, String sortByDeadline, String sortByCreatedAt) {

        List<Task> allTasks = taskRepository.findAll();

        List<Task> filteredTasks = allTasks.stream()
                .filter(task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString()))
                .collect(Collectors.toList());

        if (sortByDeadline != null && !sortByDeadline.isEmpty()) {
            filteredTasks.sort(Comparator.comparing(Task::getDeadline));
        } else if (sortByCreatedAt != null && !sortByCreatedAt.isEmpty()) {
            filteredTasks.sort(Comparator.comparing(Task::getCreatedAt));
        }

        return filteredTasks;
    }

    // Other methods remain the same as previously defined in the previous code snippet...

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
        Task existingTask = getTaskById(id);

        if(updatedTask.getTitle()!=null){
            existingTask.setTitle(updatedTask.getTitle());
        }
        if(updatedTask.getImage()!=null){
            existingTask.setImage(updatedTask.getImage());
        }
        if (updatedTask.getDescription()!=null){
            existingTask.setDescription(updatedTask.getDescription());
        }
        if(updatedTask.getStatus()!=null){
            existingTask.setStatus(updatedTask.getStatus());
        }
        if(updatedTask.getDeadline()!=null){
            existingTask.setDeadline(updatedTask.getDeadline());
        }






            return taskRepository.save(existingTask);


    }

    @Override
    public void deleteTask(Long id) {

        taskRepository.deleteById(id);

    }

    @Override
    public Task assignedToUser(Long userId,Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setStatus(TaskStatus.ASSIGNED);

        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUsersTask(Long userId,TaskStatus status, String sortByDeadline, String sortByCreatedAt) {
        List<Task> allTasks = taskRepository.findByassignedUserId(userId);


        List<Task> filteredTasks = allTasks.stream()
                .filter(task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString()))


                .collect(Collectors.toList());


        if (sortByDeadline != null && !sortByDeadline.isEmpty()) {
            filteredTasks.sort(Comparator.comparing(Task::getDeadline));
        } else if (sortByCreatedAt != null && !sortByCreatedAt.isEmpty()) {
            filteredTasks.sort(Comparator.comparing(Task::getCreatedAt));
        }

        return filteredTasks;

    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }


}
