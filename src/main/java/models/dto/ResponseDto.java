package models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Roman Nagibov
 */
@Data
@AllArgsConstructor
public class ResponseDto {

    private String URL;
    private String title;

}
