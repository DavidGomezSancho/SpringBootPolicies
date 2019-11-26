package com.home.policies.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import com.home.policies.core.exception.ElementNotFoundDBException;
import com.home.policies.dto.TakerDto;
import com.home.policies.dto.mapper.TakerMapper;
import com.home.policies.dto.validator.TakerDtoValidator;
import com.home.policies.model.entities.Taker;
import com.home.policies.repository.TakerRepository;
import com.home.policies.service.TakerService;
import com.home.policies.service.Impl.TakerServiceImpl;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TakerServiceTest {
	
	@Mock
	private TakerRepository takerRepository;
	
	@Mock
	private TakerMapper takerMapper;
	
	@Mock
	private MessageSource messageSource;
	
	@Mock
	private TakerDtoValidator takerDtoValidator;
	
	@InjectMocks
	private TakerService takerService = 
			new TakerServiceImpl(takerRepository, takerMapper, messageSource, 
					takerDtoValidator);
	
	@Test
	public void updateTakerTest() {
		TakerDto takerDto = new TakerDto();
		takerDto.setId(1L);
		Taker taker = new Taker();
		
		when(takerRepository.findByEmail(any())).thenReturn(Optional.of(taker));
		
		takerService.updateTaker(takerDto, takerDto.getId());
		
		verify(takerRepository, times(1)).save(any());
	}
	
	@Test(expected = ElementNotFoundDBException.class)
	public void updateTakerElementNotFoundTest() {
		TakerDto takerDto = new TakerDto();
		takerDto.setId(1L);
		
		when(takerRepository.findByEmail(any())).thenReturn(Optional.empty());
		
		takerService.updateTaker(takerDto, takerDto.getId());
	}

}
