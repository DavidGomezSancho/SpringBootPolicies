package com.home.policies.service;

import java.util.Collection;

import com.home.policies.dto.TakerDto;
import com.home.policies.model.entities.Taker;

public interface TakerService {
	public Taker findOrCreateTaker(TakerDto takerDto);
	public TakerDto updateTaker(TakerDto takerDto, Long idTaker);
	public TakerDto getTaker(Long idTaker);
	public Collection<TakerDto> getAllTaker();
	public TakerDto createTaker(TakerDto takerDto);
	public void deleteTaker(Long idTaker);
	public Taker getOneTaker(Long idTaker);
}
