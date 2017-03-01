package ua.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.dto.filter.ProducerFilter;
import ua.editor.CountryEditor;
import ua.entity.Country;
import ua.entity.Producer;
import ua.service.CountryService;
import ua.service.ProducerService;
import ua.validator.ProducerValidator;

@Controller
@RequestMapping("/admin/producer")
@SessionAttributes(names="producer")
public class ProducerController {
	
	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private CountryService countryService;
	
	@InitBinder("producer")
	protected void InitBinder(WebDataBinder binder){
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.setValidator(new ProducerValidator(producerService));
	}
	
	@ModelAttribute("producer")
	public Producer getForm(){
		return new Producer();
	}
	@ModelAttribute("filter")
	public ProducerFilter getFilter(){
		return new ProducerFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") ProducerFilter filter){
		model.addAttribute("page",producerService.findAll(pageable, filter));
		model.addAttribute("countries", countryService.findAll());
		return "admin-producer";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") ProducerFilter filter){
		model.addAttribute("producer", producerService.findOne(id));
		show(model, pageable, filter);
		return "admin-producer";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") ProducerFilter filter){
		producerService.delete(id);
		return "redirect:/admin/producer"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("producer")@Valid Producer producer,BindingResult br,Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") ProducerFilter filter){
		if(br.hasErrors()){
			show(model, pageable, filter);
			return "admin-producer";
		}
		producerService.save(producer);
		status.setComplete();
		return "redirect:/admin/producer"+getParams(pageable, filter);
		
	}
	
	private String getParams(Pageable pageable, ProducerFilter filter){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		if(!filter.getSearch().isEmpty()){
			buffer.append("&search=");
			buffer.append(filter.getSearch());
		}
		for (Integer id  : filter.getCountryIds()) {
			buffer.append("&countryIds=");
			buffer.append(id);
		}
		return buffer.toString();
	}
	
}
