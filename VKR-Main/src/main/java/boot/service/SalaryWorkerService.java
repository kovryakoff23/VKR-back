package boot.service;

import boot.DTO.SalaryWorkerDTO;
import boot.mapstruct.SalaryWorkerMapper;
import boot.repository.SalaryWorkerRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalaryWorkerService implements ServiceMag<SalaryWorkerDTO> {
    SalaryWorkerRepository salaryWorkerRepository;
    WorkerService workerService;

    SalaryWorkerMapper salaryWorkerMapper;

    @Autowired
    public SalaryWorkerService(SalaryWorkerRepository salaryWorkerRepository, WorkerService workerService,
                               SalaryWorkerMapper salaryWorkerMapper) {
        this.salaryWorkerRepository = salaryWorkerRepository;
        this.workerService = workerService;
        this.salaryWorkerMapper = salaryWorkerMapper;
    }

    @Override
    public SalaryWorkerDTO get(long id) {
        return salaryWorkerMapper.toDTO(salaryWorkerRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<SalaryWorkerDTO> getAll() {
        return salaryWorkerRepository.findAll().stream()
                .map(value->salaryWorkerMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(SalaryWorkerDTO entity) {
        salaryWorkerRepository.save(salaryWorkerMapper.toEntity(entity));
    }

    public void update(SalaryWorkerDTO entity) {
        salaryWorkerRepository.save(salaryWorkerMapper.toEntity(entity));
    }
    @Override
    public void delete(long id) {
        salaryWorkerRepository.deleteById(id);
    }

    public Set<SalaryWorkerDTO> getAllByDate(Date dateStartSalary, Date dateEndSalary, Long workerId){
        return workerService.get(workerId).getSalaryWorkersDTO().stream()
                .filter(SalaryWorkerDTO::isStatus)
                .filter(value1->value1.getDateSalary().after(dateStartSalary))
                .filter(value1->value1.getDateSalary().before(dateEndSalary))
                .collect(Collectors.toSet());
    }
    @Synchronized
    public List<SalaryWorkerDTO> getAllActive(){
        return salaryWorkerRepository.findByStatusTrue().stream()
                .map(value->salaryWorkerMapper.toDTO(value))
                .collect(Collectors.toList());
    }
}