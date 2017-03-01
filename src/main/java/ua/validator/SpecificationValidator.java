package ua.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.dto.form.SpecificationForm;

public class SpecificationValidator  implements Validator{
		
	
	private final static Pattern PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})|([0-9]{1,18})$");
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SpecificationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SpecificationForm specificationForm = (SpecificationForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "", "Can`t be empty");
		if(!PATTERN.matcher(specificationForm.getValue()).matches()){
			errors.rejectValue("value", "", "Wrong format, only 2 digits after separator");
		}
		
	}

}
