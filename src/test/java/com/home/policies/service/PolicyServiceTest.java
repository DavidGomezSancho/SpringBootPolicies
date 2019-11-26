package com.home.policies.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.kafka.core.KafkaTemplate;

import com.home.policies.Utils;
import com.home.policies.core.exception.ElementNotFoundDBException;
import com.home.policies.core.exception.VehicleWithPolicyException;
import com.home.policies.dto.PolicyDto;
import com.home.policies.dto.mapper.PolicyMapper;
import com.home.policies.dto.mapper.TakerMapper;
import com.home.policies.dto.mapper.VehicleMapper;
import com.home.policies.dto.validator.PolicyDtoValidator;
import com.home.policies.model.entities.Policy;
import com.home.policies.model.entities.Vehicle;
import com.home.policies.repository.PolicyRepository;
import com.home.policies.service.Impl.PolicyServiceImpl;


@RunWith(MockitoJUnitRunner.Silent.class)
public class PolicyServiceTest {
	
	@Mock
	private PolicyRepository policyRepository;
	
	@Spy
	private VehicleMapper vehicleMapper 
		= new VehicleMapper();
	
	@Spy
	private TakerMapper takerMapper 
		= new TakerMapper();
	
	@Spy
	private PolicyMapper policyMapper 
		= new PolicyMapper(takerMapper, vehicleMapper);
	
	@Mock
	private VehicleService vehicleService;
	
	@Mock
	private TakerService takerService;
	
	@Mock
	private MessageSource messageSource;
	
	@Mock
	private PolicyDtoValidator policyDtoValidator;
	
	@Mock
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@InjectMocks
	private PolicyServiceImpl policyService
	 = new PolicyServiceImpl(policyRepository, policyMapper, 
				vehicleService, takerService, messageSource, policyDtoValidator, kafkaTemplate);
	
	@Test
	public void getAllPolicyTest(){
		//Given
        List<Policy> results = Utils.getTenPolicyDummies();
        //When
        when(policyRepository.findAll()).thenReturn(results);
        List<PolicyDto> serviceResults = (List<PolicyDto>) policyService.getAllPolicy();
        //Then
        assertEquals((Integer)results.size(), new Integer(serviceResults.size()));
        assertTrue(results.get(0).getId()==serviceResults.get(0).getId());
	}
    
    @Test(expected = VehicleWithPolicyException.class)
	public void createPolicyReturnConflictWithVehicleAlreadyUsed() {
		PolicyDto policyDto = Utils.getNewpolicyDtoDummie();
		Policy policyEntity = Utils.getNewPolicyDummie();
		Vehicle vehicleEntity = new Vehicle();
		vehicleEntity.setPolicy(policyEntity);
		
		when(vehicleService.findOrCreateVehicle(any())).thenReturn(vehicleEntity);
		policyService.createPolicy(policyDto);
	}
    
    @Test
	public void createPolicyReturnCreated() {
    	
    	PolicyDto policyDto = Utils.getNewpolicyDtoDummie();
    	
    	Policy policyEntity = Utils.getNewPolicyDummie();
    	
		when(vehicleService.findOrCreateVehicle(any())).thenReturn(policyEntity.getVehicle());
		when(takerService.findOrCreateTaker(any())).thenReturn(policyEntity.getTaker());
		when(policyRepository.save(any())).thenReturn(policyEntity);
		
		policyService.createPolicy(policyDto);
		
		verify(policyRepository, times(1)).save(any());
		verify(takerService, times(1)).findOrCreateTaker(policyDto.getTaker());
		verify(vehicleService, times(1)).findOrCreateVehicle(policyDto.getVehicle());
	}
    
    @Test
    public void getPolicyTest(){
    	Policy policy = Utils.getNewPolicyDummie();
    	
    	when(policyRepository.findById(any())).thenReturn(Optional.of(policy));
    	
    	PolicyDto policyDto = policyService.getPolicy(policy.getId());
    	
    	verify(policyRepository).findById(policy.getId());
    	assertTrue(policyDto.getId()==policy.getId());
    }
    
    @Test(expected = ElementNotFoundDBException.class)
    public void getPolicyElementNotFoundTest(){
    	Long policyId = 1L;
    	
    	when(policyRepository.findById(any())).thenReturn(Optional.empty());
    	
    	policyService.getPolicy(policyId);
    }
}
