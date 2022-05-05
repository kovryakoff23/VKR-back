package boot.service;

import boot.DTO.WorkerDTO;
import boot.mapstruct.WorkerMapper;
import boot.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerService implements ServiceMag<WorkerDTO> {

    WorkerRepository workerRepository;

    WorkerMapper workerMapper;
    @Autowired
    public WorkerService(WorkerRepository workerRepository, WorkerMapper workerMapper) {
        this.workerRepository = workerRepository;
        this.workerMapper = workerMapper;
    }

    @Override
    public WorkerDTO get(long id) {
        return workerMapper.toDTO(workerRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<WorkerDTO> getAll() {
        return workerRepository.findAll().stream()
                .map(value->workerMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(WorkerDTO workerDTO) {
        workerRepository.save(workerMapper.toEntity(workerDTO));
    }

    @Override
    public void delete(long id) {
        workerRepository.deleteById(id);
    }

    public List<WorkerDTO> getAllSearch(String search) {
        search=search+"%";
        return workerRepository.findByName(search).stream()
                .map(value->workerMapper.toDTO(value))
                .collect(Collectors.toList());
    }
}