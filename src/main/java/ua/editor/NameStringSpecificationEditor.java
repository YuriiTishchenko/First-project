package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.NameStringSpecification;
import ua.service.NameStringSpecificationService;

public class NameStringSpecificationEditor extends PropertyEditorSupport {
	
private final  NameStringSpecificationService service;
	
	public  NameStringSpecificationEditor( NameStringSpecificationService service){
		this.service = service;
		
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		 NameStringSpecification  nameStringSpecification = service.findOne(Integer.valueOf(text));
		setValue( nameStringSpecification);
	}
}
