package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Country;
import ua.service.CountryService;


public class CountryValidator implements Validator {

	private final CountryService countryService;
	
	public CountryValidator (CountryService countryService){
		this.countryService=countryService;
	}
	
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Country.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Country country = (Country) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","","cant be empty");
		if(countryService.findByName(country.getName())!=null){
			errors.rejectValue("name","", "Alredy exist");
		}
		
	}

}
