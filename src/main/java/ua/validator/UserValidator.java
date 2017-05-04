package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.User;
import ua.service.UserService;

public class UserValidator implements Validator{
		private final UserService userService;
		
		

		public UserValidator(UserService userService) {
			this.userService = userService;
		}

		@Override
		public boolean supports(Class<?> clazz) {
			
			return clazz.equals(User.class);
		}

		@Override
		public void validate(Object target, Errors errors) {
			User user = (User) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "",
					"cant be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "",
					"cant be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "",
					"cant be empty");
			if (userService.findByUsername(user.getUsername()) != null) {
				errors.rejectValue("username", "", "Alredy exist");
			}
		}
}
