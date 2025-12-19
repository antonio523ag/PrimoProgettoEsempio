package dev.antoniogrillo.primoprogettoesempio.mapper;

import dev.antoniogrillo.primoprogettoesempio.dto.error.MessageErrorDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@Component
public class MessageMapper {

    public MessageErrorDTO toMessageErrorDTO(Exception e, WebRequest request){
        MessageErrorDTO messageErrorDTO = new MessageErrorDTO();
        messageErrorDTO.setMessage(e.getMessage());
        messageErrorDTO.setPath(request.getDescription(false));
        messageErrorDTO.setDataOraChiamata(LocalDateTime.now());
        return messageErrorDTO;
    }
}
