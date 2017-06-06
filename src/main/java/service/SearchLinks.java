package service;

import models.dto.QueryDto;

import java.io.IOException;
import java.util.List;

/**
 * Created by Roman Nagibov
 */
public interface SearchLinks {

	List<QueryDto> searchLinks() throws IOException;

}
