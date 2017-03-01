package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.NameStringSpecification;
import ua.service.NameStringSpecificationService;

public class NameStringSpecificationValidator implements Validator {
	
	private final NameStringSpecificationService nameStringSpecificationService;
	
	 public NameStringSpecificationValidator(NameStringSpecificationService nameStringSpecificationService) {
		this.nameStringSpecificationService = nameStringSpecificationService;
	}		
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NameStringSpecification.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NameStringSpecification nameStringSpecification = (NameStringSpecification) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","","cant be empty");
		if(nameStringSpecificationService.findByName(nameStringSpecification.getName())!=null){
			errors.rejectValue("name","", "Alredy exist");
		}
	}

}
