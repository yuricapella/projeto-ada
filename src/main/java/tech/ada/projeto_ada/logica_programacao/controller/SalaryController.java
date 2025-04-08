package tech.ada.projeto_ada.logica_programacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tech.ada.projeto_ada.logica_programacao.model.Salary;
import tech.ada.projeto_ada.logica_programacao.service.SalaryService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController() {
        this.salaryService = new SalaryService();
    }

    @GetMapping("/salario")
    public String showForm() {
        return "logica_programacao/form";
    }

    @PostMapping("/calculate")
    public String calculateSalaries(@RequestParam("salaries") String salariesInput, Model model) {
        String[] parts = salariesInput.trim().split("[ ,]+");

        List<Salary> salaryList = new ArrayList<>();
        for (String part : parts) {
            double valor = Double.parseDouble(part);
            Salary salary = new Salary(valor);
            salaryService.calcularDescontos(salary);
            salaryList.add(salary);
        }

        model.addAttribute("salaries", salaryList);
        return "logica_programacao/result";
    }
}
