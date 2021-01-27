package org.itstep.controller;

import org.itstep.controller.domain.dao.TaskDao;
import org.itstep.controller.domain.entity.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    private final TaskDao taskDao;

    public HomeController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GetMapping
    public String index(Model model) {
        List<Task> tasks = taskDao.findAll();
        if (tasks != null) {
            model.addAttribute("tasks", tasks);
        }
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        taskDao.delete(taskDao.findById(id));
        return "redirect:/";
    }

    @PostMapping
    public String create(String context, Task task) {
        switch (context) {
            case "create" :
                taskDao.save(task);
                break;
            case "update" :
                taskDao.update(task);
                break;
        }
        return "redirect:/";
    }

}
