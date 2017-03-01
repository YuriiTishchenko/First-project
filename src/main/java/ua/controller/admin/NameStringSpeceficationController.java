package ua.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import ua.dto.filter.BasicFilter;
import ua.entity.NameStringSpecification;
import ua.service.NameStringSpecificationService;
import ua.validator.NameStringSpecificationValidator;

@Controller
@RequestMapping("/admin/nameStringSpecification")
@SessionAttributes(names="nameStringSpecification")
public class NameStringSpeceficationController {

	@Autowired
	private NameStringSpecificationService nameStringSpecificationService;
	
	@ModelAttribute("nameStringSpecification")
	public NameStringSpecification getForm(){
		return new NameStringSpecification();
	}
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@InitBinder("nameStringSpecification")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new NameStringSpecificationValidator(nameStringSpecificationService));
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page",nameStringSpecificationService.findAll(pageable, filter));
		return "admin-nameStringSpecification";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter){
		nameStringSpecificationService.delete(id);
		return "redirect:/admin/nameStringSpecification";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("nameStringSpecification", nameStringSpecificationService.findOne(id));
		show(model, pageable, filter);
		return "admin-nameStringSpecification";
	}
	@PostMapping
	public String save(@ModelAttribute("nameStringSpecification")@Valid NameStringSpecification nameStringSpecification,BindingResult br,Model model, SessionStatus status, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter) {
		if(br.hasErrors()){
			show(model, pageable, filter);
			return "admin-nameStringSpecification";
		}
		nameStringSpecificationService.save(nameStringSpecification);
		status.setComplete();
		return "redirect:/admin/nameStringSpecification";
	}
}
