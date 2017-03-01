package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Specification;
import ua.service.SpecificationService;

public class SpecificationEditor  extends PropertyEditorSupport{

private final  SpecificationService service;
	
	public  SpecificationEditor( SpecificationService service){
		this.service = service;
		
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		 Specification  specification = service.findOne(Integer.valueOf(text));
		setValue( specification);
	}
	
}
