package com.home.policies.dto.mapper;

import org.springframework.stereotype.Component;

import com.home.policies.dto.TakerDto;
import com.home.policies.dto.builders.TakerDtoBuilder;
import com.home.policies.model.entities.Taker;
import com.home.policies.model.entities.builders.TakerBuilder;

@Component
public class TakerMapper {
	
	public TakerDto toDtoFromEntity(Taker taker) {
		return new TakerDtoBuilder()
				.withId(taker.getId())
				.withEmail(taker.getEmail())
				.withName(taker.getName())
				.withSurname1(taker.getSurname1())
				.WithSurname2(taker.getSurname2().orElseGet(() -> ""))
				.withTelephone(taker.getTelephone())
				.build();
	}
	
	public Taker toEntityFromDtoWithoutId(TakerDto takerDto) {
		return new TakerBuilder()
				.withEmail(takerDto.getEmail())
				.withName(takerDto.getName())
				.withSurname1(takerDto.getSurname1())
				.WithSurname2(takerDto.getSurname2())
				.withTelephone(takerDto.getTelephone())
				.build();
	}
	
	public Taker toEntityFromDtoWithId(TakerDto takerDto) {
		return new TakerBuilder()
				.withId(takerDto.getId())
				.withEmail(takerDto.getEmail())
				.withName(takerDto.getName())
				.withSurname1(takerDto.getSurname1())
				.WithSurname2(takerDto.getSurname2())
				.withTelephone(takerDto.getTelephone())
				.build();
	}
}
