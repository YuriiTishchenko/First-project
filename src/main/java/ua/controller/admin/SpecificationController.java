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

import ua.dto.filter.SpecificationFilter;
import ua.dto.form.SpecificationForm;
import ua.editor.NameStringSpecificationEditor;
import ua.entity.NameStringSpecification;
import ua.service.NameStringSpecificationService;
import ua.service.SpecificationService;
import ua.validator.SpecificationValidator;

@Controller
@RequestMapping("/admin/specification")
@SessionAttributes(names = "specification")
public class SpecificationController {

	@Autowired
	private SpecificationService specificationService;

	@Autowired
	private NameStringSpecificationService nameStringSpecificationService;

	@InitBinder("specification")
	protected void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(NameStringSpecification.class,
				new NameStringSpecificationEditor(
						nameStringSpecificationService));
		binder.setValidator(new SpecificationValidator());
	}

	@ModelAttribute("specification")
	public SpecificationForm getForm() {
		return new SpecificationForm();
	}

	@ModelAttribute("filter")
	public SpecificationFilter getFilter() {
		return new SpecificationFilter();
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") SpecificationFilter filter) {
		model.addAttribute("page",
				specificationService.findAll(pageable, filter));
		model.addAttribute("nameStringSpecifications",
				nameStringSpecificationService.findAll());
		return "admin-specification";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") SpecificationFilter filter) {
		model.addAttribute("specification", specificationService.findForm(id));
		show(model, pageable, filter);
		return "admin-specification";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") SpecificationFilter filter) {
		specificationService.delete(id);
		return "redirect:/admin/specification" + getParams(pageable, filter);
	}

	@PostMapping
	public String save(
			@ModelAttribute("specification") @Valid SpecificationForm specification,
			BindingResult br, Model model, SessionStatus status,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") SpecificationFilter filter) {
		if (br.hasErrors()) {
			show(model, pageable, filter);
			return "admin-specification";
		}
		specificationService.save(specification);
		status.setComplete();
		return "redirect:/admin/specification" + getParams(pageable, filter);
	}

	private String getParams(Pageable pageable, SpecificationFilter filter) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber() + 1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if (pageable.getSort() != null) {
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order) -> {
				buffer.append(order.getProperty());
				if (order.getDirection() != Direction.ASC)
					buffer.append(",desc");
			});
		}
		for (Integer id : filter.getNameStringSpecificationIds()) {
			buffer.append("&nameStringSpecificationIds=");
			buffer.append(id);
		}

		if (!filter.getMaxValue().isEmpty()) {
			buffer.append("&maxValue=");
			buffer.append(filter.getMaxValue());
		}
		if (!filter.getMinValue().isEmpty()) {
			buffer.append("&minValue=");
			buffer.append(filter.getMinValue());
		}
		return buffer.toString();
	}
}
