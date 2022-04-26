package boot.service;

import boot.DTO.SalaryWorkerDTO;
import boot.component.Salary;
import boot.mapstruct.SalaryWorkerMapper;
import boot.mapstruct.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalaryService {

    WorkerService workerService;

    WorkerMapper workerMapper;
    @Autowired
    public SalaryService(WorkerService workerService,  SalaryWorkerMapper salaryWorkerMapper, WorkerMapper workerMapper) {
        this.workerService = workerService;
        this.workerMapper = workerMapper;
    }

    public Set<Salary> getAll(){
        Set<Salary> salaries = workerService.getAll().stream()
                .map(value->{
                    return new Salary(workerMapper.toEntity(value),
                            value
                                    .getSalaryWorkersDTO()
                                    .stream()
                                    .filter(SalaryWorkerDTO::isStatus)
                                    .map(SalaryWorkerDTO::getSumSalary)
                                    .mapToLong(Long::longValue)
                                    .sum());
        })
                .filter(value->value.getSumSalary()>0&&value.getSumSalary()!=null)
                .collect(Collectors.toSet());
        return salaries;
    }
    public Set<Salary> getAllByDate(Date dateStartSalary, Date dateEndSalary){
        Set<Salary> salaries = workerService.getAll().stream()
                .map(value->{
                    return new Salary(workerMapper.toEntity(value),
                            value
                                    .getSalaryWorkersDTO()
                                    .stream()
                                    .filter(SalaryWorkerDTO::isStatus)
                                    .filter(value1->value1.getDateSalary().after(dateStartSalary))
                                    .filter(value1->value1.getDateSalary().before(dateEndSalary))
                                    .filter(SalaryWorkerDTO::isStatus)
                                    .map(SalaryWorkerDTO::getSumSalary)
                                    .mapToLong(Long::longValue)
                                    .sum());
                })
                .filter(value->value.getSumSalary()>0&&value.getSumSalary()!=null)
                .collect(Collectors.toSet());
        return salaries;
    }
}
