package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class DefaultController {

    @Autowired
    private TaskRepository taskRepository;

    @Value("${someParameter.value}")
    private Integer someParameter;

    @RequestMapping("/")
    public String index(Model model) {

        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks =  new ArrayList<>((Collection<? extends Task>) taskIterable);
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        model.addAttribute("someParameter", someParameter);
        return "index";
    }
}