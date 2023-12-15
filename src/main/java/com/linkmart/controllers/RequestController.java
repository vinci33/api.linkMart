package com.linkmart.controllers;

import com.linkmart.dtos.OneRequestDto;
import com.linkmart.dtos.RequestDto;
import com.linkmart.dtos.RequestResponseWithMessageDto;
import com.linkmart.dtos.ResponseWithMessage;
import com.linkmart.models.RequestModel;
import com.linkmart.services.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class RequestController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RequestService requestService;

    @Autowired
    HttpServletRequest request;

    @PostMapping(value = "/api/request", consumes = {"multipart/form-data"})
    public RequestModel postRequest (
            @RequestParam(value = "locationId") Integer locationId,
            @RequestParam(value = "categoryId") Integer categoryId,
            @RequestParam(value = "itemDetail", required = false) String itemDetail,
            @RequestParam(value = "item") String item,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "quantity") Integer quantity,
            @RequestParam(value = "offerPrice", required = false) Integer offerPrice,
            @RequestParam(value = "requestRemark", required = false) String requestRemark,
            @RequestParam(value = "imageFile") List<MultipartFile> file) {
        try{
            var userId = (String)request.getAttribute("userId");
            RequestModel request = requestService.postRequest(userId, locationId, categoryId, itemDetail, item, url,
                    quantity, requestRemark,offerPrice, file);
            return request;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/request")
    public List<RequestDto> getAllRequest () {
        try{
            return requestService.getAllRequest();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/api/request")
    public List<RequestDto> getAllMyRequest (HttpServletRequest request) {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info(userId);
            return requestService.getAllMyRequest(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/request/{requestId}")
    public OneRequestDto getOneRequest (@PathVariable(value = "requestId") String requestId) {
        try{
            var result = requestService.getOneRequest(requestId);
            return result;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "/api/request/{requestId}")
    public RequestResponseWithMessageDto deleteRequest (HttpServletRequest request, @PathVariable(value = "requestId") String requestId) {
        try{
            var userId = (String)request.getAttribute("userId");
            requestService.deleteRequest(requestId, userId);
            return new RequestResponseWithMessageDto("Delete request success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
