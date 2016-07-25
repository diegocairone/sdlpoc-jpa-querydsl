package com.cairone.sdlpocjpa.dtos.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cairone.sdlpocjpa.dtos.ProvinciaFrmDto;

@Component
public class ProvinciaFrmDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (ProvinciaFrmDto.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "id", "required", new Object[] {"ID DE LA PROVINCIA"});
		ValidationUtils.rejectIfEmpty(errors, "paisID", "required", new Object[] {"ID DEL PAIS AL QUE PERTENCE LA PROVINCIA"});
		ValidationUtils.rejectIfEmpty(errors, "descripcion", "required", new Object[] {"NOMBRE DE LA PROVINCIA"});
	}
}
