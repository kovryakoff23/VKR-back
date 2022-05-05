package boot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class SuppliersDTO {
    private Long id;
    private String name;
    private String numberAccount;
    private String bic;
    private String kpp;
    private String inn;
    private String address;
    private String contactPhoneNumber;
    private String email;

}
