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

import ua.dto.filter.ItemFilter;
import ua.dto.form.ItemForm;
import ua.editor.DescriptionEditor;
import ua.editor.NameDetailEditor;
import ua.editor.ProducerEditor;
import ua.editor.SpecificationEditor;
import ua.editor.TypeDetailEditor;
import ua.entity.Description;
import ua.entity.NameDetail;
import ua.entity.Producer;
import ua.entity.Specification;
import ua.entity.TypeDetail;
import ua.service.DescriptionService;
import ua.service.FileWriter;
import ua.service.ItemService;
import ua.service.NameDetailService;
import ua.service.ProducerService;
import ua.service.SpecificationService;
import ua.service.TypeDetailService;
import ua.validator.ItemValidator;

@Controller
@RequestMapping("/admin/item")
@SessionAttributes(names = "item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ProducerService producerService;

	@Autowired
	private NameDetailService nameDetailService;

	@Autowired
	private TypeDetailService typeDetailService;

	@Autowired
	private SpecificationService specificationService;
	
	@Autowired
	private DescriptionService descriptionService;
	
	@Autowired
	private FileWriter fileWriter;

	@InitBinder("item")
	protected void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(NameDetail.class, new NameDetailEditor(
				nameDetailService));
		binder.registerCustomEditor(TypeDetail.class, new TypeDetailEditor(
				typeDetailService));
		binder.registerCustomEditor(Specification.class,
				new SpecificationEditor(specificationService));
		binder.registerCustomEditor(Producer.class, new ProducerEditor(
				producerService));
		binder.registerCustomEditor(Description.class, new DescriptionEditor(
				descriptionService));
		binder.setValidator(new ItemValidator());
	}

	@ModelAttribute("item")
	public ItemForm getForm() {
		return new ItemForm();
	}

	@ModelAttribute("filter")
	public ItemFilter getFilter() {
		return new ItemFilter();
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") ItemFilter filter) {
		model.addAttribute("page", itemService.findAll(pageable, filter));
		model.addAttribute("producers", producerService.findAll());
		model.addAttribute("nameDetails", nameDetailService.findAll());
		model.addAttribute("typeDetails", typeDetailService.findAll());
		model.addAttribute("specifications", specificationService.findAll());
		model.addAttribute("descriptions",descriptionService.findAll());
		return "admin-item";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") ItemFilter filter) {
		model.addAttribute("item", itemService.findOne(id));
		show(model, pageable, filter);
		return "admin-item";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") ItemFilter filter) {
		itemService.delete(id);
		return "redirect:/admin/item"+getParams(pageable, filter);
	}

	@PostMapping
	public String save(@ModelAttribute("item") @Valid ItemForm item,
			BindingResult br, Model model, SessionStatus status,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") ItemFilter filter) {
		if (br.hasErrors()) {
			show(model, pageable, filter);
			return "admin-item";
		}
		itemService.save(item);
		status.setComplete();
		return "redirect:/admin/item"+getParams(pageable, filter);
	}

	private String getParams(Pageable pageable, ItemFilter filter) {
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
		if (!filter.getMaxPrice().isEmpty()) {
			buffer.append("&maxPrice=");
			buffer.append(filter.getMaxPrice());
		}
		if (!filter.getMinPrice().isEmpty()) {
			buffer.append("&minPrice=");
			buffer.append(filter.getMinPrice());
		}
		for (Integer id : filter.getProducerIds()) {
			buffer.append("&producerIds=");
			buffer.append(id);
		}
		for (Integer id : filter.getNameDetailIds()) {
			buffer.append("&nameDetailIds=");
			buffer.append(id);
		}
		for (Integer id : filter.getTypeDetailIds()) {
			buffer.append("&typeDetailIds=");
			buffer.append(id);
		}
		
		return buffer.toString();
	}
}
