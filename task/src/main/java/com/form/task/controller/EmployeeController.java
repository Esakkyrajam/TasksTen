package com.form.task.controller;


import com.form.task.model.Employee;
import com.form.task.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // 1. Landing page form
    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

    // 2. Save employee
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/displayAll";
    }

    // 3. Display all
    @GetMapping("/displayAll")
    @ResponseBody
    public Iterable<Employee> displayAll() {
        return employeeRepository.findAll();
    }

    // 4. Display by ID
    @GetMapping("/display/{id}")
    @ResponseBody
    public Optional<Employee> displayById(@PathVariable String id) {
        return employeeRepository.findById(id);
    }
}