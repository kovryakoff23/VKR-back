package boot.service;

import boot.DTO.SuppliersDTO;
import boot.mapstruct.SuppliersMapper;
import boot.repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuppliersService implements ServiceMag<SuppliersDTO>{
    SuppliersRepository suppliersRepository;
    SuppliersMapper suppliersMapper;

    @Autowired
    public SuppliersService(SuppliersRepository suppliersRepository,
                            SuppliersMapper suppliersMapper) {
        this.suppliersRepository = suppliersRepository;
        this.suppliersMapper = suppliersMapper;
    }

    @Override
    public SuppliersDTO get(long id) {
        return suppliersMapper.toDTO(suppliersRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<SuppliersDTO> getAll() {

        return suppliersRepository.findAll().stream()
                .map(value->suppliersMapper.toDTO(value))
                .collect(Collectors.toList());
    }

    @Override
    public void save(SuppliersDTO entity) {
        suppliersRepository.save(suppliersMapper.toEntity(entity));
    }

    @Override
    public void delete(long id) {
        suppliersRepository.deleteById(id);
    }

    public List<SuppliersDTO> getAllSearch(String search) {
        search=search+"%";
        return (suppliersRepository.findByName(search)).stream()
                .map(value->suppliersMapper.toDTO(value))
                .collect(Collectors.toList());
    }
}
