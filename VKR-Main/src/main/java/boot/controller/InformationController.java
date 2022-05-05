package boot.controller;

import boot.component.FileInfo;
import boot.component.InfoReports;
import boot.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InformationController {

   InformationService informationService;

   @Autowired
    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/information")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfo = informationService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(InformationController.class, "getFileInfo", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
    }
    @GetMapping("/information/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFileInfo(@PathVariable String filename) {
        Resource file = informationService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/information/reports")
    public InfoReports informationReports() {
        return informationService.loadReports();
    }
}
