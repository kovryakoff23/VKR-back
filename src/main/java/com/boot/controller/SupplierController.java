package com.boot.controller;

import com.boot.component.Payment;
import com.boot.entity.*;
import com.boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierController {

    SuppliersService suppliersService;
    SupplierPositionsService supplierPositionsService;
    PaymentService paymentService;
    PaymentSupplierService paymentSupplierService;

    @Autowired
    public SupplierController(SuppliersService suppliersService, SupplierPositionsService supplierPositionsService, PaymentService paymentService, PaymentSupplierService paymentSupplierService) {
        this.suppliersService = suppliersService;
        this.supplierPositionsService = supplierPositionsService;
        this.paymentService = paymentService;
        this.paymentSupplierService = paymentSupplierService;
    }

    @GetMapping("/suppliersPayment")
    public Set<Payment> getPayment() {

        return  paymentService.getAll();
    }

    @RequestMapping(value="/suppliersPayment/" , method=RequestMethod.GET)
    @ResponseBody
    public Set<Payment> getPaymentByDate(@RequestParam("dateStartPay") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStartPay,
                                             @RequestParam("dateEndPay") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEndPay) {
        return paymentService.getAllByDate(dateStartPay,dateEndPay);
    }

    @GetMapping("/suppliers")
    public List<Suppliers> getSupplier() {
        return  suppliersService.getAll();
    }
    @GetMapping("/suppliers/search/{search}")
    public List<Suppliers> Suppliers(@PathVariable("search") String search) {
        List<Suppliers> suppliersListSearch =  new ArrayList<>();
        suppliersListSearch = suppliersService.getAllSearch(search);
        return  suppliersListSearch;

    }
    @GetMapping("/suppliers/{suppliersId}")
    public Suppliers getSuppliers(@PathVariable("suppliersId") Long suppliersId) {
        return suppliersService.get(suppliersId);
    }
    @PostMapping("/suppliers")
    public void saveSupplier(@RequestBody Suppliers suppliers) {
        try {
            suppliersService.save(suppliers);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/suppliers/{supplierId}")
    public void deleteSupplier(@PathVariable("supplierId") Long supplierId) {
        try {
            suppliersService.delete(supplierId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @GetMapping("/suppliersPositions/{supplierId}")
    public Set<SupplierPositions> getSupplierPositions(@PathVariable("supplierId") Long supplierId) {
        return suppliersService.get(supplierId).getSupplyPositions();
    }
    @GetMapping("/suppliersPositions/byId/{supplierId}")
    public SupplierPositions getSupplierPositionsById(@PathVariable("supplierId") Long supplierId) {
        return supplierPositionsService.get(supplierId);
    }
    @PostMapping("/suppliersPositions")
    public void saveSupplierPositions(@RequestBody SupplierPositions supplierPosition) {
        try {
            supplierPositionsService.save(supplierPosition);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/suppliersPositions/{supplierId}")
    public void deleteSupplierPositions(@PathVariable("supplierId") Long supplierId) {
        try {
                supplierPositionsService.delete(supplierId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @GetMapping("/suppliersPositions/payment/{supplierId}")
    public Set<PaymentSupplier> getPaymentSupplier(@PathVariable("supplierId") Long supplierId) {
        return suppliersService.get(supplierId).getPaymentSuppliers();
    }
    @PostMapping("/suppliersPositions/payment")
    public void savePaymentSupplier(@RequestBody PaymentSupplier paymentSupplier) {
        try {
            paymentSupplierService.save(paymentSupplier);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PutMapping("/suppliersPositions/payment")
    public void updatePaymentSupplier(@RequestBody PaymentSupplier paymentSupplier) {
        try {
            paymentSupplierService.update(paymentSupplier);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/suppliersPositions/payment/{paymentSupplierId}")
    public void deletePaymentSupplier(@PathVariable("paymentSupplierId") Long paymentSupplierId) {
//        try {
            paymentSupplierService.delete(paymentSupplierId);
//        }catch(Exception e){
//            System.out.println("error input");
//        }
    }
    @RequestMapping(value="/suppliersPositions/payment/" , method=RequestMethod.GET)
    @ResponseBody
    public Set<PaymentSupplier> getPaymentSupplierByDate(@RequestParam("dateStartPay") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStartPay,
                                                   @RequestParam("dateEndPay") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEndPay,
                                                   @RequestParam("supplierId") Long supplierId) {
        return paymentSupplierService.getAllByDate(dateStartPay,dateEndPay, supplierId);
    }
}
