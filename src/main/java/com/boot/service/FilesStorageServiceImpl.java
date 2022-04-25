package com.boot.service;

import com.boot.DTO.DocumentationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    DocumentationService documentationService;

    UnitService unitService;
    private final Path root = Paths.get("uploads");

    @Autowired
    public FilesStorageServiceImpl(DocumentationService documentationService, UnitService unitService) {
        this.documentationService = documentationService;
        this.unitService = unitService;
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    @Override
    public void save(MultipartFile file, String unitId) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            documentationService.save(new DocumentationDTO(
                    file.getOriginalFilename(),
                    unitService.get(Long.parseLong(unitId))));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    @Override
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
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
    @Override
    public Stream<Path> loadAllById(Long unitId) {
        try {
            Set<String> fileNames = unitService.get(unitId).getDocumentationsDTO().stream()
                    .map(DocumentationDTO::getName).collect(Collectors.toSet());
            return Files.walk(this.root, 1)
                    .filter(path -> !path.equals(this.root))
                    .filter(path -> fileNames.contains(path.getName(1).getFileName().toString()))
                    .map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
