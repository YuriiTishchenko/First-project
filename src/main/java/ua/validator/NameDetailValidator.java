package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.NameDetail;
import ua.service.NameDetailService;

public class NameDetailValidator implements Validator {
	private final NameDetailService nameDetailService;
	
	 public NameDetailValidator(NameDetailService nameDetailService) {
		this.nameDetailService = nameDetailService;
	}	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NameDetail.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NameDetail nameDetail = (NameDetail) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","","cant be empty");
		if(nameDetailService.findByName(nameDetail.getName())!=null){
			errors.rejectValue("name","", "Alredy exist");
		}		
	}

}
