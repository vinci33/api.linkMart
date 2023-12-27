package com.linkmart.controllers;

import com.linkmart.dtos.*;
import com.linkmart.forms.ProviderBioForm;
import com.linkmart.models.Provider;
import com.linkmart.services.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Tag(name = "Provider", description = """
**Provider APIs**

- Represents the ProviderController class, which is responsible for handling various API endpoints related to providers.

- It includes methods for enrolling a provider, retrieving provider details, changing provider biography, and creating a new provider.
""")
@RestController
public class ProviderController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProviderService providerService;

    private final HttpServletRequest request;

    public ProviderController (ProviderService providerService, HttpServletRequest request){
        this.providerService = providerService;
        this.request = request;
    };



    @Operation(summary = "Get Provider List",
            description = "Get Provider List",
            tags = { "Provider","Post"})
    @PostMapping("/provider/{locationId}")
    public ResponseWithMessage enrollProvider(@PathVariable Integer locationId, HttpServletRequest request) {
      try {
          var userId = (String)request.getAttribute("userId");
            var providerId = providerService.enrollProvider(userId,locationId);
            return new ResponseWithMessage(true, "Provider had been created ProviderId: " + providerId);
        }catch (IllegalArgumentException e) {
          logger.error(e.getMessage());
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
    }

//    @PostMapping("/api/provider")
//    public ResponseWithMessage applyProvider(HttpServletRequest request,
//                                 @RequestParam(value = "locationId") Integer locationId,
//                                 @RequestParam(value = "addressDocument")MultipartFile addressDocument,
//                                 @RequestParam(value = "idDocument")MultipartFile idDocument,
//                                 @RequestParam(value = "bankDocument")MultipartFile bankDocument) {
//      try {
//          var userId = (String)request.getAttribute("userId");
//          if (userId == null) {
//              throw new IllegalArgumentException("Invalid UserId");
//          }
//          if (locationId == null) {
//              throw new IllegalArgumentException("Invalid LocationId");
//          }
//          if (addressDocument == null) {
//                throw new IllegalArgumentException("Invalid Address Document");
//            }
//          if (idDocument == null) {
//                throw new IllegalArgumentException("Invalid Id Document");
//            }
//          if (bankDocument == null) {
//                throw new IllegalArgumentException("Invalid Bank Document");
//            }
//          providerService.providerApplication(userId, addressDocument, idDocument, bankDocument, locationId);
//          return new ResponseWithMessage(true, "User had applied as provider");
//        }catch (IllegalArgumentException e) {
//          logger.error(e.getMessage());
//          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//      }
//    }

    @GetMapping("/api/provider")
    public VerificationResponseDto getProviderApplicationDetail(HttpServletRequest request) {
      try {
          var userId = (String)request.getAttribute("userId");
          if (userId == null) {
              throw new IllegalArgumentException("Invalid UserId");
          }
          var verificationDetail = providerService.getProviderVerificationDetail(userId);
          if (verificationDetail == null) {
              return new VerificationResponseDto(null);
          }
          return new VerificationResponseDto(verificationDetail);
        }catch (IllegalArgumentException e) {
          logger.error(e.getMessage());
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
    }

    //10.4.1
    @GetMapping("/api/provider/profile")
    public ProviderDetailDto getProviderProfile(HttpServletRequest request) {
      try {
          var userId = (String)request.getAttribute("userId");
          if (userId == null) {
              throw new IllegalArgumentException("Invalid UserId");
          }
          return providerService.showProviderDetailByUserId(userId);
        }catch (IllegalArgumentException e) {
          logger.error(e.getMessage());
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
    }

    @PutMapping("/api/provider/profile")
    public void changeProviderBio(HttpServletRequest request,@RequestBody ProviderBioForm providerBioForm) {
        try {
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("Invalid UserId");
            }
            String bio = providerBioForm.getBiography();
            providerService.changeProviderBio(userId, bio);
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //10.4.2
    @GetMapping("/provider/profile/{providerId}")
    public ProviderDetailDto publicGetProviderProfile(@PathVariable String providerId) {
        try {
            return providerService.publicShowProviderDetailByUserId(providerId);
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //10.5 Get Provider Dashboard
    @GetMapping("/api/provider/dashboard")
    public ProviderDashboardDto getProviderDashboard(HttpServletRequest request) {
        try {
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("Invalid UserId");
            }
            return providerService.getProviderDashboard(userId);
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/api/provider")
    public ResponseEntity<Map<String, String>> createProvider(@RequestParam(value = "locationId") Integer locationId,
                                                              @RequestParam(value = "addressDocument")MultipartFile addressDocument,
                                                              @RequestParam(value = "idDocument")MultipartFile idDocument,
                                                              @RequestParam(value = "bankDocument")MultipartFile bankDocument) {
        try {
            var userId = (String)request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("Invalid UserId");
            }
            providerService.createProvider(userId, locationId, addressDocument, idDocument, bankDocument);
            return ResponseEntity.ok(Map.of("message", "Provider had been created"));
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
