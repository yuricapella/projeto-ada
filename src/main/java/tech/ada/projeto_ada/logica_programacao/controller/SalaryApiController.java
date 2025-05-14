package tech.ada.projeto_ada.logica_programacao.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.ada.projeto_ada.logica_programacao.model.Salary;
import tech.ada.projeto_ada.logica_programacao.service.SalaryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/logica-programacao")
public class SalaryApiController {

    private final SalaryService salaryService;

    public SalaryApiController() {
        this.salaryService = new SalaryService();
    }

    @PostMapping("/salarios")
    public List<Salary> calculateSalaries(
            @RequestBody @Valid List<Salary> salarios,
            @RequestParam(value = "dependentes", defaultValue = "0") int dependentes) {

        List<Salary> salaryList = new ArrayList<>();

        for (Salary salary : salarios) {
            salary.setNumeroDependentes(dependentes);
            salaryService.calcularDescontos(salary);
            salaryList.add(salary);
        }

        return salaryList;
    }
}
