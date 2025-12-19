package dev.antoniogrillo.primoprogettoesempio.exception;

import dev.antoniogrillo.primoprogettoesempio.dto.error.MessageErrorDTO;
import dev.antoniogrillo.primoprogettoesempio.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class CustomRestControllerAdvice {

    @Autowired
    private MessageMapper messageMapper;

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MessageErrorDTO> sqlIntegrityConstraintViolationExceptionHandler(
            SQLIntegrityConstraintViolationException e, WebRequest request){
        MessageErrorDTO messageErrorDTO = messageMapper.toMessageErrorDTO(e, request);
        return new ResponseEntity<>(messageErrorDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UtenteException.class)
    public ResponseEntity<MessageErrorDTO> utenteExcepionHandler(
            UtenteException e, WebRequest request){
        MessageErrorDTO dto=messageMapper.toMessageErrorDTO(e,request);
        return ResponseEntity.badRequest().body(dto);
    }
}
