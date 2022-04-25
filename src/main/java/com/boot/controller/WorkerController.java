package com.boot.controller;

import com.boot.DTO.PricingWorkerDTO;
import com.boot.DTO.SalaryWorkerDTO;
import com.boot.DTO.WorkerDTO;
import com.boot.component.Salary;
import com.boot.service.PricingWorkerService;
import com.boot.service.SalaryService;
import com.boot.service.SalaryWorkerService;
import com.boot.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WorkerController {
    WorkerService workerService;
    PricingWorkerService pricingWorkerService;
    SalaryService salaryService;
    SalaryWorkerService salaryWorkerService;

    @Autowired
    public WorkerController(WorkerService workerService, PricingWorkerService pricingWorkerService, SalaryService salaryService, SalaryWorkerService salaryWorkerService) {
        this.workerService = workerService;
        this.pricingWorkerService = pricingWorkerService;
        this.salaryService = salaryService;
        this.salaryWorkerService = salaryWorkerService;
    }

    @GetMapping("/workersSalary")
    public Set<Salary> getWorkerSalary() {
        return  salaryService.getAll();
    }
    @RequestMapping(value="/workersSalary/" , method=RequestMethod.GET)
    @ResponseBody
    public Set<Salary> getWorkerSalaryByDate(@RequestParam("dateStartSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStartSalary,
                                             @RequestParam("dateEndSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEndSalary) {
        return salaryService.getAllByDate(dateStartSalary,dateEndSalary);
    }
    @GetMapping("/workers")
    public List<WorkerDTO> getWorker() {
        return  workerService.getAll();
    }
    @GetMapping("/workers/search/{search}")
    public List<WorkerDTO> Worker(@PathVariable("search") String search) {
        return  workerService.getAllSearch(search);

    }
    @GetMapping("/workers/{workerId}")
    public WorkerDTO getWorker(@PathVariable("workerId") Long workerId) {
        return workerService.get(workerId);
    }

    @PostMapping("/workers")
    public void saveWorker(@RequestBody WorkerDTO worker) {
        try {
            workerService.save(worker);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/workers/{workerId}")
    public void deleteWorker(@PathVariable("workerId") Long workerId) {
        try {
            workerService.delete(workerId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @GetMapping("/workerDetails/{pricingWorkerId}")
    public Set<PricingWorkerDTO> getPricingWorker(@PathVariable("pricingWorkerId") Long pricingWorkerId) {
        return workerService.get(pricingWorkerId).getPricingWorkersDTO();
    }
    @GetMapping("/workerDetails/byId/{pricingWorkerId}")
    public PricingWorkerDTO getPricingWorkerById(@PathVariable("pricingWorkerId") Long pricingWorkerId) {
        return pricingWorkerService.get(pricingWorkerId);
    }
    @PostMapping("/workerDetails")
    public void savePricingWorker(@RequestBody PricingWorkerDTO pricingWorker) {
        try {
            pricingWorkerService.save(pricingWorker);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/workerDetails/{workerDetailsId}")
    public void deletePricingWorker(@PathVariable("workerDetailsId") Long workerDetailsId) {
            pricingWorkerService.delete(workerDetailsId);
    }
    @GetMapping("/worker/salary/{workerId}")
    public Set<SalaryWorkerDTO> getSalaryWorker(@PathVariable("workerId") Long workerId) {
        return workerService.get(workerId).getSalaryWorkersDTO();
    }
    @GetMapping("/worker/salary")
    public List<SalaryWorkerDTO> getSalaryWorkerHistory() {
        return salaryWorkerService.getAll();
    }
    @PostMapping("/worker/salary/")
    public void saveSalaryWorker(@RequestBody SalaryWorkerDTO salaryWorker) {
        try {
            salaryWorkerService.save(salaryWorker);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PutMapping("/worker/salary/")
    public void updateSalaryWorker(@RequestBody SalaryWorkerDTO salaryWorker) {
        try {
            salaryWorkerService.update(salaryWorker);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/worker/salary/{salaryWorkerId}")
    public void deleteSalaryWorker(@PathVariable("salaryWorkerId") Long salaryWorkerId) {
        try {
            salaryWorkerService.delete(salaryWorkerId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @RequestMapping(value="/worker/salary/" , method=RequestMethod.GET)
    @ResponseBody
    public Set<SalaryWorkerDTO> getSalaryWorkerByDate(@RequestParam("dateStartSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStartSalary,
                                             @RequestParam("dateEndSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEndSalary,
                                             @RequestParam("workerId") Long workerId) {
        return salaryWorkerService.getAllByDate(dateStartSalary,dateEndSalary, workerId);
    }
}
