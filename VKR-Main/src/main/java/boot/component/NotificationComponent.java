package boot.component;

import boot.DTO.NotificationDTO;
import boot.DTO.PaymentSupplierDTO;
import boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Component
public class NotificationComponent {
    @Autowired
    UnitService unitService;
    @Autowired
    UnitProductionService unitProductionService;
    @Autowired
    UnitDeliveriesService unitDeliveriesService;
    @Autowired
    SalaryWorkerService salaryWorkerService;
    @Autowired
    RestTemplate restTemplate;
//    PaymentSupplierService paymentSupplierService;

    String urlRequest = "http://localhost:8091/suppliersPositions/getAllActivePayment";
    @Autowired
    NotificationService notificationService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void updateProduct() {
        Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                notificationService.deleteAll();
                LocalDate now = LocalDate.now().plusDays(3);
                unitService.getAllActive().forEach(value->{
                        if(now.compareTo(value.getDateFinish().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())==0){
                            String message = "До окончания сроков на объекте " +value.getName()+
                            " осталось три дня";
                            notificationService.save(new NotificationDTO(new Date(),1,message));
                        }
                });
                LocalDate startNow = LocalDate.now().plusDays(2);;
                unitProductionService.getAllActive().forEach(value->{
                    if(startNow.compareTo(value.getDateStartWork().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())==0){
                        String message = "Через два дня на объекте " +value.getUnitDTO().getName()+
                                " начнется производство работ " + value.getNameWork();
                        notificationService.save(new NotificationDTO(new Date(),2,message));
                    }
                });
                LocalDate finalNow = LocalDate.now().plusDays(2);;
                unitProductionService.getAllActive().forEach(value->{
                    if(finalNow.compareTo(value.getDateEndWork().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())==0){
                        String message = "Через два дня на объекте " +value.getUnitDTO().getName()+
                                " должно завершиться производство работ " + value.getNameWork();
                        notificationService.save(new NotificationDTO(new Date(),2,message));
                    }
                });
                LocalDate finalAfter = LocalDate.now();;
                unitProductionService.getAllActive().forEach(value->{
                    if(finalAfter.compareTo(value.getDateEndWork().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())>0){
                        String message = "На объекте " +value.getUnitDTO().getName()+
                                " производство работ " + value.getNameWork() + " не было завершено в срок";
                        notificationService.save(new NotificationDTO(new Date(),2,message));
                    }
                });
                LocalDate finalDeliveriesNow = LocalDate.now().plusDays(2);;
                unitDeliveriesService.getAllActive().forEach(value->{
                    if(finalDeliveriesNow.compareTo(value.getDateComplete().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())==0){
                        String message = "Через два дня на объекте " +value.getUnitDTO().getName()+
                                " ожидается поставка " + value.getName();
                        notificationService.save(new NotificationDTO(new Date(),3,message));
                    }
                });
                LocalDate finalDeliverAfter = LocalDate.now();;
                unitDeliveriesService.getAllActive().forEach(value->{
                    if(finalDeliverAfter.compareTo(value.getDateComplete().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())>0){
                        String message = "На объекте " +value.getUnitDTO().getName()+
                                " пставка " + value.getName() + " не была выполнена в ожидаемый срок";
                        notificationService.save(new NotificationDTO(new Date(),3,message));
                    }
                });
                LocalDate salaryDay = LocalDate.now();;
                salaryWorkerService.getAllActive().forEach(value->{
                    if(salaryDay.compareTo(value.getDateSalary().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())>=0){
                        String message = "Рабочий " +value.getWorkerDTO().getName()+
                                " ожидает оплаты " + value.getNamePosition();
                        notificationService.save(new NotificationDTO(new Date(),4,message));
                    }
                });
                LocalDate paymentDay = LocalDate.now();;
                ResponseEntity<PaymentSupplierDTO[]> responseEntity = restTemplate.getForEntity(urlRequest, PaymentSupplierDTO[].class);
                PaymentSupplierDTO[] paymentSupplierDTOArray = responseEntity.getBody();
                assert paymentSupplierDTOArray != null;
                List<PaymentSupplierDTO> paymentSupplierDTOList = Arrays.stream(paymentSupplierDTOArray)
                        .collect(Collectors.toList());
                paymentSupplierDTOList.forEach(value->{
                    if(paymentDay.compareTo(value.getDatePay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())>=0){
                        String message = "Поставщик " +value.getSuppliersDTO().getName()+
                                " ожидает оплаты " + value.getNamePosition();
                        notificationService.save(new NotificationDTO(new Date(),4,message));
                    }
                });
            }
        });
        worker.setDaemon(true);
        worker.start();
    }
}
