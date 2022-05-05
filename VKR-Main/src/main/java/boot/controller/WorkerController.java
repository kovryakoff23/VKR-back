package boot.controller;

import boot.DTO.PricingWorkerDTO;
import boot.DTO.SalaryWorkerDTO;
import boot.DTO.WorkerDTO;
import boot.component.Salary;
import boot.service.PricingWorkerService;
import boot.service.SalaryService;
import boot.service.SalaryWorkerService;
import boot.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping()
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
    @PreAuthorize("hasRole('USER')")
    public Set<Salary> getWorkerSalary() {
        return  salaryService.getAll();
    }
    @RequestMapping(value="/workersSalary/" , method=RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public Set<Salary> getWorkerSalaryByDate(@RequestParam("dateStartSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStartSalary,
                                             @RequestParam("dateEndSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEndSalary) {
        return salaryService.getAllByDate(dateStartSalary,dateEndSalary);
    }
    @GetMapping("workers")
    @PreAuthorize("hasRole('USER')")
    public List<WorkerDTO> getWorker() {
        return  workerService.getAll();
    }
    @GetMapping("/workers/search/{search}")
    @PreAuthorize("hasRole('USER')")
    public List<WorkerDTO> Worker(@PathVariable("search") String search) {
        return  workerService.getAllSearch(search);

    }
    @GetMapping("workers/{workerId}")
    @PreAuthorize("hasRole('USER')")
    public WorkerDTO getWorker(@PathVariable("workerId") Long workerId) {
        return workerService.get(workerId);
    }

    @PostMapping("workers")
    @PreAuthorize("hasRole('USER')")
    public void saveWorker(@RequestBody WorkerDTO worker) {
        try {
            workerService.save(worker);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/workers/{workerId}")
    @PreAuthorize("hasRole('USER')")
    public void deleteWorker(@PathVariable("workerId") Long workerId) {
        try {
            workerService.delete(workerId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @GetMapping("/workerDetails/{pricingWorkerId}")
    @PreAuthorize("hasRole('USER')")
    public Set<PricingWorkerDTO> getPricingWorker(@PathVariable("pricingWorkerId") Long pricingWorkerId) {
        return workerService.get(pricingWorkerId).getPricingWorkersDTO();
    }
    @GetMapping("/workerDetails/byId/{pricingWorkerId}")
    @PreAuthorize("hasRole('USER')")
    public PricingWorkerDTO getPricingWorkerById(@PathVariable("pricingWorkerId") Long pricingWorkerId) {
        return pricingWorkerService.get(pricingWorkerId);
    }
    @PostMapping("/workerDetails")
    @PreAuthorize("hasRole('USER')")
    public void savePricingWorker(@RequestBody PricingWorkerDTO pricingWorker) {
        try {
            pricingWorkerService.save(pricingWorker);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/workerDetails/{workerDetailsId}")
    @PreAuthorize("hasRole('USER')")
    public void deletePricingWorker(@PathVariable("workerDetailsId") Long workerDetailsId) {
            pricingWorkerService.delete(workerDetailsId);
    }
    @GetMapping("/worker/salary/{workerId}")
    @PreAuthorize("hasRole('USER')")
    public Set<SalaryWorkerDTO> getSalaryWorker(@PathVariable("workerId") Long workerId) {
        return workerService.get(workerId).getSalaryWorkersDTO();
    }
    @GetMapping("/worker/salary")
    @PreAuthorize("hasRole('USER')")
    public List<SalaryWorkerDTO> getSalaryWorkerHistory() {
        return salaryWorkerService.getAll();
    }
    @PostMapping("/worker/salary/")
    @PreAuthorize("hasRole('USER')")
    public void saveSalaryWorker(@RequestBody SalaryWorkerDTO salaryWorker) {
        try {
            salaryWorkerService.save(salaryWorker);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PutMapping("/worker/salary/")
    @PreAuthorize("hasRole('USER')")
    public void updateSalaryWorker(@RequestBody SalaryWorkerDTO salaryWorker) {
        try {
            salaryWorkerService.update(salaryWorker);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/worker/salary/{salaryWorkerId}")
    @PreAuthorize("hasRole('USER')")
    public void deleteSalaryWorker(@PathVariable("salaryWorkerId") Long salaryWorkerId) {
        try {
            salaryWorkerService.delete(salaryWorkerId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @RequestMapping(value="/worker/salary/" , method=RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public Set<SalaryWorkerDTO> getSalaryWorkerByDate(@RequestParam("dateStartSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStartSalary,
                                             @RequestParam("dateEndSalary") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEndSalary,
                                             @RequestParam("workerId") Long workerId) {
        return salaryWorkerService.getAllByDate(dateStartSalary,dateEndSalary, workerId);
    }
}
