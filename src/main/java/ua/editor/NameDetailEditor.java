package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.NameDetail;
import ua.service.NameDetailService;

public class NameDetailEditor extends PropertyEditorSupport {
	private final NameDetailService service;
	
	public NameDetailEditor(NameDetailService service){
		this.service = service;
		
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		NameDetail nameDetail = service.findOne(Integer.valueOf(text));
		setValue(nameDetail);
	}

}
