package com.linkmart.dtos;

import java.util.List;

public record HasOfferDto (Boolean hasOffer, List<OfferCheckDto> offer) {
}
