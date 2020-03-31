package demo.student.exception;

import demo.student.controller.dto.ErrorDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class AppException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        ErrorDto errorDto = ErrorDto.builder()
                .error("Not Found")
                .messages(List.of(e.getMessage()))
                .build();
        return handleExceptionInternal(e, errorDto, headers, HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleEmptyResult(EmptyResultDataAccessException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        ErrorDto errorDto = ErrorDto.builder()
                .error("Not Found on Update")
                .messages(List.of(e.getMessage()))
                .build();
        return handleExceptionInternal(e, errorDto, headers, HttpStatus.BAD_REQUEST, request);
    }
}


