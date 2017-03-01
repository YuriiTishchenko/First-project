package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.TypeDetail;
import ua.service.TypeDetailService;

public class TypeDetailEditor extends PropertyEditorSupport{
	
	private final TypeDetailService service;
	
	public TypeDetailEditor(TypeDetailService service){
		this.service = service;
		
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		TypeDetail typeDetail = service.findOne(Integer.valueOf(text));
		setValue(typeDetail);
	}

}
