package com.boot.controller;

import com.boot.entity.Unit;
import com.boot.service.UnitService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
    @Autowired
    UnitService unitService;
    @GetMapping("/home")
    public void getHome() {
    }
}
