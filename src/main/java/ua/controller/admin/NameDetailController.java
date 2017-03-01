package ua.controller.admin;

import static ua.service.utils.ParamBuilder.getParams;

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
import ua.entity.NameDetail;
import ua.service.NameDetailService;
import ua.validator.NameDetailValidator;

@Controller
@RequestMapping("/admin/nameDetail")
@SessionAttributes(names="nameDetail")
public class NameDetailController {
	
	@Autowired
	private NameDetailService nameDetailService;
	
	@ModelAttribute("nameDetail")
	public NameDetail getForm(){
		return new NameDetail();
	}
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	@InitBinder("nameDetail")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new NameDetailValidator(nameDetailService));
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page",nameDetailService.findAll(pageable, filter));
		return "admin-nameDetail";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter){
		nameDetailService.delete(id);
		return "redirect:/admin/nameDetail"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,@PageableDefault Pageable pageable,
			@ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("nameDetail", nameDetailService.findOne(id));
		show(model, pageable, filter);
		return "admin-nameDetail";
	}
	@PostMapping
	public String save(@ModelAttribute("nameDetail")@Valid NameDetail nameDetail,BindingResult br,Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter, SessionStatus status) {
		if(br.hasErrors()){
			show(model, pageable, filter);
			return "admin-nameDetail";
		}
		nameDetailService.save(nameDetail);
		status.setComplete();
		return "redirect:/admin/nameDetail"+getParams(pageable, filter);
	}
}
