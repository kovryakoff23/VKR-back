package com.boot.controller;

import com.boot.component.Salary;
import com.boot.entity.*;
import com.boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @RequestMapping(value="/workersSalar/" , method=RequestMethod.GET)
    @ResponseBody
    public Set<Salary> getWorkerSalaryByDate(@RequestParam("dateStartSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStartSalary,
                                             @RequestParam("dateEndSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEndSalary) {
        return salaryService.getAllByDate(dateStartSalary,dateEndSalary);
    }
    @GetMapping("/workers")
    public List<Worker> getWorker() {
        return  workerService.getAll();
    }
    @GetMapping("/workers/search/{search}")
    public List<Worker> Worker(@PathVariable("search") String search) {
        List<Worker> workersListSearch =  new ArrayList<>();
        workersListSearch = workerService.getAllSearch(search);
        return  workersListSearch;

    }
    @GetMapping("/workers/{workerId}")
    public Worker getWorker(@PathVariable("workerId") Long workerId) {
        return workerService.get(workerId);
    }

    @PostMapping("/workers")
    public void saveWorker(@RequestBody Worker worker) {
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
    public Set<PricingWorker> getPricingWorker(@PathVariable("pricingWorkerId") Long pricingWorkerId) {
        return workerService.get(pricingWorkerId).getPricingWorkers();
    }
    @GetMapping("/workerDetails/byId/{pricingWorkerId}")
    public PricingWorker getPricingWorkerById(@PathVariable("pricingWorkerId") Long pricingWorkerId) {
        return pricingWorkerService.get(pricingWorkerId);
    }
    @PostMapping("/workerDetails")
    public void savePricingWorker(@RequestBody PricingWorker pricingWorker) {
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
    public Set<SalaryWorker> getSalaryWorker(@PathVariable("workerId") Long workerId) {
        return workerService.get(workerId).getSalaryWorkers();
    }
    @PostMapping("/worker/salary/")
    public void saveSalaryWorker(@RequestBody SalaryWorker salaryWorker) {
        try {
            salaryWorkerService.save(salaryWorker);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PutMapping("/worker/salary/")
    public void updateSalaryWorker(@RequestBody SalaryWorker salaryWorker) {
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
    public Set<SalaryWorker> getSalaryWorkerByDate(@RequestParam("dateStartSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStartSalary,
                                             @RequestParam("dateEndSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEndSalary,
                                             @RequestParam("workerId") Long workerId) {
        return salaryWorkerService.getAllByDate(dateStartSalary,dateEndSalary, workerId);
    }
}
