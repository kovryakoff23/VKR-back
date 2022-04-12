package com.boot.service;

import com.boot.component.InfoReports;
import com.boot.entity.PaymentSupplier;
import com.boot.entity.SalaryWorker;
import com.boot.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class InformationService {
    @Autowired
    UnitService unitService;
    @Autowired
    SuppliersService suppliersService;
    @Autowired
    WorkerService workerService;
    @Autowired
    SalaryWorkerService salaryWorkerService;
    @Autowired
    PaymentSupplierService paymentSupplierService;

    private final Path root = Paths.get("Information");

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
                .filter(SalaryWorker::isStatus)
                .filter(value->!value.isStatusExecution())
                .map(SalaryWorker::getSumSalary)
                .mapToLong(Long::longValue)
                .sum();
        Long sumPayment = paymentSupplierService.getAll().stream()
                .filter(PaymentSupplier::isStatus)
                .filter(value->!value.isStatusExecution())
                .map(PaymentSupplier::getSumPay)
                .mapToLong(Long::longValue)
                .sum();;
        Long countUnit = (long) unitService.getAllActive().size();
        Long countUnitActive = (long) unitService.getAll().size();
        Long quantityWorker = (long) workerService.getAll().size();
        Long quantitySuppliers = (long) suppliersService.getAll().size();
        infoReports = new InfoReports(sumSalary,sumPayment,countUnit,
                countUnitActive, quantityWorker, quantitySuppliers
        );
        return infoReports;
    }
}