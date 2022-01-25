package PU.puservice.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExResult {

    private Date timestamp;
    private String code;
    private String message;
    private String details;
}
