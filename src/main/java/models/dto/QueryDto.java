package models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Roman Nagibov
 */
@Data
@AllArgsConstructor
public class QueryDto {

    String queryUri;
    String title;

}
