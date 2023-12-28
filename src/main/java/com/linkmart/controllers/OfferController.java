package com.linkmart.controllers;

import com.linkmart.dtos.*;
import com.linkmart.forms.AcceptOfferForm;
import com.linkmart.forms.EditOfferForm;
import com.linkmart.forms.OfferForm;
import com.linkmart.services.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Tag(name = "Offer", description = """
        **Offer APIs**

        - Represents the OfferController class, which is responsible for handling various API endpoints related to offers by provider.

        - It interacts with the OfferService class to perform operations
        such as creating an offer, retrieving offers by request ID, updating an offer, and deleting an offer by provider.
                 
        """)
@RestController
public class OfferController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OfferService offerService;
    private final HttpServletRequest request;

    public OfferController(OfferService offerService, HttpServletRequest request) {
        this.offerService = offerService;
        this.request = request;
    }

    @Operation(summary = "Create offer",
            description = """
            Creates a new offer by extracting the necessary information from the OfferForm object.
            Returns a RequestResponseWithMessageDto object with a success message.
            """,
            tags = { "Offer","Post"})
    @PostMapping(value = "/api/offer")
    public RequestResponseWithMessageDto postOffer (@RequestBody OfferForm offerForm)
            throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            var requestId = offerForm.getRequestId();
            if (requestId == null) {
                throw new Exception("Request id is null");
            }
            var price = offerForm.getPrice();
            if (price == null) {
                throw new Exception("Price is null");
            }
            var estimatedProcessTime = offerForm.getEstimatedProcessTime();
            if (estimatedProcessTime == null) {
                throw new Exception("Estimated process time is null");
            }
            var offerRemark = offerForm.getOfferRemark();
            if (offerRemark == null) {
                throw new Exception("Offer remark is null");
            }
            offerService.postOffer(userId, requestId, price, estimatedProcessTime, offerRemark);
            return new RequestResponseWithMessageDto("Offer created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @Operation(summary = "Get offer by request id",
            description = """
            Retrieves a list of offers by extracting the necessary information from the GetOneOfferDto object.
            Returns a list of GetOneOfferDto objects.
            """,
            tags = { "Offer","Get"})
    //for requester
    @GetMapping(value = "/api/offer/request/{requestId}")
    public List<GetOneOfferDto> getOfferByRequestId (
            @PathVariable(value = "requestId") String requestId) throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            return offerService.getOfferByRequestId(userId, requestId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Get my offer",
            description = """
            Retrieves a list of offers by extracting the necessary information from the OfferDto object.
            Returns a list of OfferDto objects.
            """,
            tags = { "Offer","Get"})
    //for provider
    @GetMapping(value = "/api/offer/myOffer")
    public List<OfferDto> getMyOffer () throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            return offerService.getMyOffer(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot get offer in controller");
        }
    }
    @Operation(summary = "Update offer",
            description = """
            Updates an offer with Offer id by extracting the necessary information from the EditOfferForm object.
            Returns a RequestResponseWithMessageDto object with a success message.
            """,
            tags = { "Offer","Put"})
    //ToDo RequestBody
    @PutMapping(value = "/api/offer/{offerId}")
    public RequestResponseWithMessageDto updateOffer (
            @PathVariable(value = "offerId", required = false) String offerId,
            @RequestBody EditOfferForm editOfferForm)
            throws Exception {
        try{
            var userId = (String)request.getAttribute("userId");
            logger.info("userId: " + userId);
            Integer price = editOfferForm.getPrice();
            Integer estimatedProcessTime = editOfferForm.getEstimatedProcessTime();
            String offerRemark = editOfferForm.getOfferRemark();
            offerService.updateOffer(userId, offerId, price, estimatedProcessTime, offerRemark);
            return new RequestResponseWithMessageDto("Offer updated successfully");
        } catch (Exception e) {
            throw new Exception("Cannot update offer in controller");
        }
    }


    @Operation(summary = "Accept offer",
            description = """
            Accepts an offer with Offer id and Address id by extracting the necessary information.
            Returns a PaymentDetailDto object.
            """,
            tags = { "Offer","Get"})
    @GetMapping(value = "/api/offer/paymentInfo/{offerId}/{addressId}")
    public PaymentDetailDto acceptOffer (
            @PathVariable("offerId") String offerId,
            @PathVariable("addressId") Integer addressId,
            HttpServletRequest request) {
        try{
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            PaymentDetailDto paymentDetail = offerService.acceptOffer(userId, offerId, addressId);
            return paymentDetail;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


  @Operation(summary = "Get offer detail",
            description = """
            Retrieves an offer with Offer id by extracting the necessary information.
            Returns an OfferDetailDto object.
            """,
            tags = { "Offer","Get"})
    //6.1.3 get offer detail
    @GetMapping(value = "/api/offer/{offerId}")
    public OfferDetailDto getOneOffer (
            @PathVariable("offerId") String offerId) {
        try{
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            return  offerService.getOfferByOfferId(userId, offerId);

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Redirect offer",
            description = """
            Create an offer with Offer id by extracting the necessary information.
            Returns a redirect link, offerId, addressId and price with PaymentDto object.
            """,
            tags = { "Offer","Post"})
    @PostMapping(value = "/api/offer/{offerId}")
    public PaymentDto redirectOffer (
            @RequestBody AcceptOfferForm acceptOfferForm,
            @PathVariable("offerId") String offerId) {
        try{
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            Integer addressId = acceptOfferForm.getUserAddressId();
            Integer price = offerService.getOfferPriceByOfferId(offerId);
//            String redirectUrl = "https://api.fight2gether.com/api/offer/paymentInfo/" + offerId + "/" + addressId;
            String redirectUrl = "/user/payment/" + offerId +"?addressId=" + addressId + "&price=" + price;
            return new PaymentDto(redirectUrl, offerId, addressId, price);
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Delete offer",
            description = """
            Deletes an offer with Offer id by extracting the necessary information.
            And Set Offer status id to 3 (aborted).
            """,
            tags = { "Offer","Delete"})
    @DeleteMapping(value = "/api/offer/{offerId}/aborted")
    public void deleteOffer (
            @PathVariable("offerId") String offerId) {
        try{
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            offerService.deleteOffer(userId, offerId);
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Reject offer",
            description = """
            Rejects an offer with Offer id by extracting the necessary information.
            And Set Offer status id to 4 (rejected).
            """,
            tags = { "Offer","Delete"})
    @DeleteMapping(value = "/api/offer/{offerId}/rejected")
    public void rejectOffer (
            @PathVariable("offerId") String offerId) {
        try{
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("UserId not found");
            }
            offerService.requestRejectedByUser(userId, offerId);
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
