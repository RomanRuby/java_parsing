package service;

import models.dto.ResponseDto;

import java.io.IOException;

/**
 * Created by Roman Nagibov
 */
public interface SearchLinks {

	ResponseDto search() throws IOException;

}
