package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.TypeDetail;
import ua.service.TypeDetailService;

public class TypeDetailValidator  implements Validator{
	
	private final TypeDetailService typeDetailService;
	
	 public TypeDetailValidator(TypeDetailService typeDetailService) {
		this.typeDetailService = typeDetailService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return TypeDetail.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TypeDetail typeDetail = (TypeDetail) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","","cant be empty");
		if(typeDetailService.findByName(typeDetail.getName())!=null){
			errors.rejectValue("name","", "Alredy exist");
		}
	}

}
