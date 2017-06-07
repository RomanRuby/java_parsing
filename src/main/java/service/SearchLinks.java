package service;

import models.dto.ResponseDto;

import java.io.IOException;
import java.util.List;

/**
 * Created by Roman Nagibov
 */
public interface SearchLinks {

	List<ResponseDto> searchLinks() throws IOException;

}
