package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Producer;
import ua.service.ProducerService;

public class ProducerEditor extends PropertyEditorSupport {
	
private final  ProducerService service;
	
	public  ProducerEditor( ProducerService service){
		this.service = service;
		
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		Producer  producer = service.findOne(Integer.valueOf(text));
		setValue( producer);
	}
}
