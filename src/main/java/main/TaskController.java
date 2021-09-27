package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/todos")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public List<Task> list(){
        Iterable<Task> taskIterable = taskRepository.findAll();
        return new ArrayList<>((Collection<? extends Task>) taskIterable);
    }

    @PostMapping("/")
    public int add(Task task){
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id){
        Task task = findByID(id);
        if (task == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(Task task){
        Task taskFound = findByID(task.getId());
        if(taskFound == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        Task task = findByID(id);
        if(task == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.delete(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public void deleteAll(){
        taskRepository.deleteAll();
    }

    private Task findByID(int id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }


}

