package boot.service;

import boot.DTO.PaymentSupplierDTO;
import boot.DTO.SalaryWorkerDTO;
import boot.DTO.SuppliersDTO;
import boot.component.InfoReports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InformationService {
    UnitService unitService;
    WorkerService workerService;
    SalaryWorkerService salaryWorkerService;
    RestTemplate restTemplate;
    String urlRequestPayment = "http://localhost:8091/suppliersPositions/payment";
    String urlRequestSuppliers = "http://localhost:8091/suppliers";
    private final Path root = Paths.get("Information");

    @Autowired
    public InformationService(UnitService unitService, RestTemplate restTemplate, WorkerService workerService, SalaryWorkerService salaryWorkerService) {
        this.unitService = unitService;
        this.workerService = workerService;
        this.salaryWorkerService = salaryWorkerService;
        this.restTemplate = restTemplate;
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
    public InfoReports loadReports(){
        InfoReports infoReports;
        Long sumSalary = salaryWorkerService.getAll().stream()
                .filter(SalaryWorkerDTO::isStatus)
                .filter(value->!value.isStatusExecution())
                .map(SalaryWorkerDTO::getSumSalary)
                .mapToLong(Long::longValue)
                .sum();
        ResponseEntity<PaymentSupplierDTO[]> responseEntity = restTemplate.getForEntity(urlRequestPayment, PaymentSupplierDTO[].class);
        PaymentSupplierDTO[] paymentSupplierDTOArray = responseEntity.getBody();
        assert paymentSupplierDTOArray != null;
        List<PaymentSupplierDTO> paymentSupplierDTOList = Arrays.stream(paymentSupplierDTOArray)
                .collect(Collectors.toList());
        Long sumPayment = paymentSupplierDTOList.stream()
                .filter(PaymentSupplierDTO::isStatus)
                .filter(value->!value.isStatusExecution())
                .map(PaymentSupplierDTO::getSumPay)
                .mapToLong(Long::longValue)
                .sum();;
        Long countUnit = (long) unitService.getAllActive().size();
        Long countUnitActive = (long) unitService.getAll().size();
        Long quantityWorker = (long) workerService.getAll().size();
        ResponseEntity<SuppliersDTO[]> responseEntitySuppliers = restTemplate.getForEntity(urlRequestSuppliers, SuppliersDTO[].class);
        SuppliersDTO[] supplierDTOArray = responseEntitySuppliers.getBody();
        assert supplierDTOArray != null;
        List<SuppliersDTO> supplierDTOList = Arrays.stream(supplierDTOArray)
                .collect(Collectors.toList());
        Long quantitySuppliers = (long) supplierDTOList.size();
        infoReports = new InfoReports(sumSalary,sumPayment,countUnit,
                countUnitActive, quantityWorker, quantitySuppliers
        );
        return infoReports;
    }
}
