package com.home.policies.service.Impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.home.policies.core.exception.ElementAlreadyExistsInDB;
import com.home.policies.core.exception.ElementNotFoundDBException;
import com.home.policies.core.exception.TakerWithPolicyException;
import com.home.policies.dto.TakerDto;
import com.home.policies.dto.mapper.TakerMapper;
import com.home.policies.dto.validator.TakerDtoValidator;
import com.home.policies.model.entities.Taker;
import com.home.policies.repository.TakerRepository;
import com.home.policies.service.TakerService;

@Service
public class TakerServiceImpl implements TakerService{
	
	private TakerRepository takerRepository;
	private TakerMapper takerMapper;
	private MessageSource messageSource;
	private TakerDtoValidator takerDtoValidator;
	
	@Autowired
	public TakerServiceImpl(TakerRepository takerRepository, 
			TakerMapper takerMapper, MessageSource messageSource,
			TakerDtoValidator takerDtoValidator) {
		this.takerRepository = takerRepository;
		this.takerMapper = takerMapper;
		this.messageSource = messageSource;
		this.takerDtoValidator = takerDtoValidator;
	}

	@Override
	public Taker findOrCreateTaker(TakerDto takerDto) {
		return takerRepository.findByEmail(takerDto.getEmail())
				.orElseGet(() -> takerRepository.save(
						takerMapper.toEntityFromDtoWithoutId(takerDto)));
	}

	@Override
	public TakerDto updateTaker(TakerDto takerDto, Long idTaker) {
		takerDtoValidator.validatePut(takerDto, idTaker);
		takerRepository.findByEmail(takerDto.getEmail())
				.orElseThrow(()->new ElementNotFoundDBException(messageSource));
		return takerMapper.toDtoFromEntity(
				takerRepository.save(
						takerMapper.toEntityFromDtoWithId(takerDto)));
	}

	@Override
	public TakerDto getTaker(Long idTaker) {
		return takerMapper.toDtoFromEntity(takerRepository.findById(idTaker)
				.orElseThrow(() -> new ElementNotFoundDBException(messageSource)));
	}

	@Override
	public Collection<TakerDto> getAllTaker() {
		return takerRepository.findAll().stream()
				.map((taker) -> takerMapper.toDtoFromEntity(taker))
				.collect(Collectors.toList());
	}

	@Override
	public TakerDto createTaker(TakerDto takerDto) {
		if(takerRepository.existsByEmail(takerDto.getEmail())) {
			throw new ElementAlreadyExistsInDB(messageSource);
		}
		return takerMapper.toDtoFromEntity(
				takerRepository.save(takerMapper.toEntityFromDtoWithoutId(takerDto)));
	}

	@Override
	public void deleteTaker(Long idTaker) {
		if(takerRepository.findAllPolicyByTakerTrue(idTaker))
			throw new TakerWithPolicyException(messageSource);
		
		if(takerRepository.existsById(idTaker)) {
			takerRepository.deleteById(idTaker);
		}else {
			throw new ElementNotFoundDBException(messageSource);
		}
	}
	
	@Override
	public Taker getOneTaker(Long idTaker) {
		return takerRepository.getOne(idTaker);
	}

}
