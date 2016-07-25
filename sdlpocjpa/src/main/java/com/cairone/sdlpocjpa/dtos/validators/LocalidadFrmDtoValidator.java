package com.cairone.sdlpocjpa.dtos.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cairone.sdlpocjpa.dtos.LocalidadFrmDto;

@Component
public class LocalidadFrmDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (LocalidadFrmDto.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "paisID", "required", new Object[] {"ID DEL PAIS AL QUE PERTENCE LA LOCALIDAD"});
		ValidationUtils.rejectIfEmpty(errors, "provinciaID", "required", new Object[] {"ID DE LA PROVINCIA AL QUE PERTENCE LA LOCALIDAD"});
		ValidationUtils.rejectIfEmpty(errors, "codigoPostal", "required", new Object[] {"CODIGO POSTAL DE LA LOCALIDAD"});
		ValidationUtils.rejectIfEmpty(errors, "descripcion", "required", new Object[] {"NOMBRE DE LA LOCALIDAD"});
		
	}
}
