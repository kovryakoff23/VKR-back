package boot.controller;

import boot.DTO.PaymentSupplierDTO;
import boot.DTO.SupplierPositionsDTO;
import boot.DTO.SuppliersDTO;
import boot.DTO.UnitDeliveriesPositionDTO;
import boot.component.Payment;
import boot.service.PaymentService;
import boot.service.PaymentSupplierService;
import boot.service.SupplierPositionsService;
import boot.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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
    public List<SuppliersDTO> getSupplier() {
        return  suppliersService.getAll();
    }


    @GetMapping("/suppliers/search/{search}")
    public List<SuppliersDTO> Suppliers(@PathVariable("search") String search) {
        return  suppliersService.getAllSearch(search);

    }
    @GetMapping("/suppliers/{suppliersId}")
    public SuppliersDTO getSuppliers(@PathVariable("suppliersId") Long suppliersId) {
        return suppliersService.get(suppliersId);
    }
    @PostMapping("/suppliers")
    public void saveSupplier(@RequestBody SuppliersDTO suppliers) {
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
    public Set<SupplierPositionsDTO> getSupplierPositions(@PathVariable("supplierId") Long supplierId) {
        return suppliersService.get(supplierId).getSupplyPositionsDTO();
    }
    @GetMapping("/suppliersPositions/byId/{supplierId}")
    public SupplierPositionsDTO getSupplierPositionsById(@PathVariable("supplierId") Long supplierId) {
        return supplierPositionsService.get(supplierId);
    }
    @PostMapping("/suppliersPositions")
    public void saveSupplierPositions(@RequestBody SupplierPositionsDTO supplierPosition) {
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
    public Set<PaymentSupplierDTO> getPaymentSupplier(@PathVariable("supplierId") Long supplierId) {
        return suppliersService.get(supplierId).getPaymentSuppliersDTO();
    }
    @GetMapping("/suppliersPositions/payment")
    public List<PaymentSupplierDTO> getPaymentSupplierHistory() {
        return paymentSupplierService.getAll();
    }
    @PostMapping("/suppliersPositions/payment")
    public void savePaymentSupplier(@RequestBody PaymentSupplierDTO paymentSupplier) {
        try {
            paymentSupplierService.save(paymentSupplier);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @PutMapping("/suppliersPositions/payment")
    public void updatePaymentSupplier(@RequestBody PaymentSupplierDTO paymentSupplier) {
        try {
            paymentSupplierService.update(paymentSupplier);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/suppliersPositions/payment/{paymentSupplierId}")
    public void deletePaymentSupplier(@PathVariable("paymentSupplierId") Long paymentSupplierId) {
        try {
            paymentSupplierService.delete(paymentSupplierId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @RequestMapping(value="/suppliersPositions/payment/" , method=RequestMethod.GET)
    @ResponseBody
    public Set<PaymentSupplierDTO > getPaymentSupplierByDate(@RequestParam("dateStartPay") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStartPay,
                                                   @RequestParam("dateEndPay") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEndPay,
                                                   @RequestParam("supplierId") Long supplierId) {
        return paymentSupplierService.getAllByDate(dateStartPay,dateEndPay, supplierId);
    }

    @PutMapping("suppliersPositions/unitDeliveriesPosition")
    public void updateUnitDeliveriesPosition(@RequestBody UnitDeliveriesPositionDTO unitDeliveriesPositionDTO) {
        try {
            paymentSupplierService.updatePaymentSupplier(unitDeliveriesPositionDTO);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PostMapping ("suppliersPositions/unitDeliveriesPosition")
    public void saveUnitDeliveriesPosition(@RequestBody UnitDeliveriesPositionDTO unitDeliveriesPositionDTO) {
        try {
            paymentSupplierService.createPaymentSupplier(unitDeliveriesPositionDTO);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @GetMapping("/suppliersPositions/getAllActivePayment")
    public List<PaymentSupplierDTO> getAllActivePayment() {
        return paymentSupplierService.getAllActive();
    }

}
