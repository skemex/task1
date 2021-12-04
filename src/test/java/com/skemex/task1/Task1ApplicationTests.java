package com.skemex.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.skemex.task1.dto.QuotationDTO;
import com.skemex.task1.map.QuotationMapper;
import com.skemex.task1.model.Quotation;

@SpringBootTest
class Task1ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void whenConvertModelToDto_thenCorrect() {
		Quotation model = new Quotation(100.0, 4, 10.0);
		QuotationDTO dto = QuotationMapper.toDto(model);

		assertEquals(model.getAmount(), dto.getAmount());
		assertEquals(model.getRate(), dto.getRate());
		assertEquals(model.getTerms(), dto.getTerms());
	}

	@Test
	void whenConvertDtoToModel_thenCorrect() {
		QuotationDTO dto = new QuotationDTO(100.0, 4, 10.0);
		Quotation model = QuotationMapper.toModel(dto);
		
		assertEquals(model.getAmount(), dto.getAmount());
		assertEquals(model.getRate(), dto.getRate());
		assertEquals(model.getTerms(), dto.getTerms());
	}
}
