package ua.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.dto.filter.ItemFilter;
import ua.entity.User;
import ua.repository.UserRepository;
import ua.service.DescriptionService;
import ua.service.ItemService;
import ua.service.NameDetailService;
import ua.service.ProducerService;
import ua.service.SpecificationService;
import ua.service.TypeDetailService;
import ua.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes(names = "index")
public class IndexController {
	
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
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String index(Principal principal, Model model, @PageableDefault Pageable pageable,@ModelAttribute("filter") ItemFilter filter){
		if(principal!=null)
			System.out.println(principal.getName());
		model.addAttribute("page", itemService.findAll(pageable, filter));
		model.addAttribute("producers", producerService.findAll());
		model.addAttribute("nameDetails", nameDetailService.findAll());
		model.addAttribute("typeDetails", typeDetailService.findAll());
		model.addAttribute("specifications", specificationService.findAll());
		model.addAttribute("descriptions",descriptionService.findAll());
		return "user-index";
	}
	
	@RequestMapping("/typeDetailPage/{id}")
	 public String typeDetailPage(@PathVariable(value = "id") int id , Model model, @PageableDefault Pageable pageable,@ModelAttribute("filter") ItemFilter filter){
		System.out.println("start");
		filter.getTypeDetailIds().add(id);
		model.addAttribute("page", itemService.findAll(pageable, filter));
		model.addAttribute("producers", producerService.findAll());
		model.addAttribute("typeDetails", typeDetailService.findAll());
		model.addAttribute("nameDetails", nameDetailService.findAll());
		model.addAttribute("specifications", specificationService.findAll());
		model.addAttribute("descriptions",descriptionService.findAll());
		return "user-typeDetailPage";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin-admin";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "user-login";
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("userForm", new User());
		return "user-registration";
	}
	
	@RequestMapping(value="/registration", method=POST)
	public String registration(@ModelAttribute User user){
		userService.save(user);
		return "redirect:/";
	}
	@RequestMapping(value="/pageItem/{id}")
	public String showItemDetail(@PathVariable(value = "id")  int id, Model model){
		model.addAttribute("item", itemService.findOne(id));
		return "user-pageItem";
	}
	
	@RequestMapping(value = "/ordernow/{id}")
	public String ordernow(@PathVariable(value = "id") int id, Principal principal) {
		userService.addItem(id, principal);
		return "redirect:/";

	}

	
	@RequestMapping("/shoppingCart")
	public String show(Model model, Principal principal) {	
	    model.addAttribute("carts",userService.findByUsername(principal.getName()));  
		return "user-shoppingCart";
	}
	
	@RequestMapping(value = "/shoppingCart/delete/{id}", method = RequestMethod.GET)
	public String deleteItems(@PathVariable(value = "id") int id, Principal principal) {
		userService.deleteItems(id, principal);
		return "redirect:/shoppingCart/";

	}
}