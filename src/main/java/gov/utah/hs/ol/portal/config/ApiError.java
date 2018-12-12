package gov.utah.hs.ol.portal.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

    private int status;
    private String message;
    private String developerMessage;

}
