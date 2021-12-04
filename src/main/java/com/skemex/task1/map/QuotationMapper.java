package com.skemex.task1.map;

import com.skemex.task1.dto.QuotationDTO;
import com.skemex.task1.model.Quotation;

public class QuotationMapper {

	public static QuotationDTO toDto(final Quotation quotation) {
		return new QuotationDTO(quotation.getId(), quotation.getAmount(), quotation.getTerms(), quotation.getRate());
	}

	public static Quotation toModel(final QuotationDTO quotationDTO) {
		return new Quotation(quotationDTO.getAmount(), quotationDTO.getTerms(), quotationDTO.getRate());
	}

}
