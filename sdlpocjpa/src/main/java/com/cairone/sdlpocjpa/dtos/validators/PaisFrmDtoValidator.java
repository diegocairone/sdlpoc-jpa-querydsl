package com.cairone.sdlpocjpa.dtos.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cairone.sdlpocjpa.dtos.PaisFrmDto;

@Component
public class PaisFrmDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (PaisFrmDto.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "id", "required", new Object[] {"ID DEL PAIS"});
		ValidationUtils.rejectIfEmpty(errors, "descripcion", "required", new Object[] {"NOMBRE DEL PAIS"});
	}
}
