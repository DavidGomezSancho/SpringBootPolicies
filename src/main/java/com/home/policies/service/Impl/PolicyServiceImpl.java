package com.home.policies.service.Impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.policies.core.exception.ElementNotFoundDBException;
import com.home.policies.core.exception.VehicleWithPolicyException;
import com.home.policies.dto.PolicyDto;
import com.home.policies.dto.mapper.PolicyMapper;
import com.home.policies.dto.validator.PolicyDtoValidator;
import com.home.policies.model.entities.Policy;
import com.home.policies.model.entities.Taker;
import com.home.policies.model.entities.Vehicle;
import com.home.policies.repository.PolicyRepository;
import com.home.policies.service.PolicyService;
import com.home.policies.service.TakerService;
import com.home.policies.service.VehicleService;

@Service
@CacheConfig(cacheNames={"policy"})
public class PolicyServiceImpl implements PolicyService{
	
	private PolicyRepository policyRepository;
	private PolicyMapper policyMapper;
	private VehicleService vehicleService;
	private TakerService takerService;
	private MessageSource messageSource;
	private PolicyDtoValidator policyDtoValidator;
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyService.class);
	private static final String TOPIC = "policies";
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	public PolicyServiceImpl(PolicyRepository policyRepository, 
			PolicyMapper policyMapper, VehicleService vehicleService,
			TakerService takerService, MessageSource messageSource, 
			PolicyDtoValidator policyDtoValidator,
			KafkaTemplate<String, Object> kafkaTemplate) {
		this.policyRepository=policyRepository;
		this.policyMapper=policyMapper;
		this.vehicleService=vehicleService;
		this.takerService = takerService;
		this.messageSource=messageSource;
		this.policyDtoValidator = policyDtoValidator;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	@Cacheable(key = "#policyId")
	public PolicyDto getPolicy(Long policyId) {
		return policyMapper.toDtoFromEntity(
				policyRepository.findById(policyId)
					.orElseThrow(() -> new ElementNotFoundDBException(messageSource)));
	}
	
	@Override
	@Transactional
	public PolicyDto createPolicy(PolicyDto policyDto) {
		policyDtoValidator.validatePost(policyDto);
		Policy policyToCreate = policyMapper.toEntityFromDtoWithoutId(policyDto);
		
		Vehicle vehicleToInsert = vehicleService.findOrCreateVehicle(policyDto.getVehicle());
		vehicleToInsert.getPolicy()
			.ifPresent((p) -> {
				throw new VehicleWithPolicyException(messageSource, vehicleToInsert.getLicensePlate());
			});
		policyToCreate.setVehicle(vehicleToInsert);
		
		Taker takerToInsert = takerService.findOrCreateTaker(policyDto.getTaker());
		policyToCreate.setTaker(takerToInsert);
		
		return policyMapper.toDtoFromEntity(
				policyRepository.save(policyToCreate));
	}

	@Override
	public Collection<PolicyDto> getAllPolicy() {
		return policyRepository.findAll().parallelStream()
				.map((policy)->policyMapper.toDtoFromEntity(policy))
				.collect(Collectors.toList());
	}
	
	@Override
	@CachePut(key = "#idPolicy")
	public PolicyDto updatePolicy(PolicyDto policyDto, Long idPolicy) {
		policyDtoValidator.validatePut(policyDto, idPolicy);
		policyRepository.findById(idPolicy)
				.orElseThrow(()->new ElementNotFoundDBException(messageSource));
		
		PolicyDto policyDtoSaved = policyMapper.toDtoFromEntity(
				policyRepository.save(
						policyMapper.toEntityFromDtoWithId(policyDto)));
		//When update, send an advice using Kafka
		logger.info(String.format("Sending advice and updating policy with id: %d",idPolicy));
		this.kafkaTemplate.send(TOPIC, policyDtoSaved);
		return policyDtoSaved;
	}

	@Override
	@CacheEvict(key = "#idPolicy")
	public void deletePolicy(Long idPolicy) {
		if(policyRepository.existsById(idPolicy)) {
			//Custom method
			policyRepository.deleteById(idPolicy);
		}else {
			throw new ElementNotFoundDBException(messageSource);
		}
		
//Less efficient, it retrieves all the entity from the database
//		policyRepository.delete(
//				policyRepository.findById(idPolicy)
//				.orElseThrow(() -> new ElementNotFoundDBException(messageSource)));
	}

}
