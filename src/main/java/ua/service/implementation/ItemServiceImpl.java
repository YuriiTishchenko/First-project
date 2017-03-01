package ua.service.implementation;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.ItemFilter;
import ua.dto.form.ItemForm;
import ua.entity.Item;
import ua.entity.User;
import ua.repository.DescriptionRepository;
import ua.repository.ItemRepository;
import ua.repository.NameDetailRepository;
import ua.repository.ProducerRepository;
import ua.repository.SpecificationRepository;
import ua.repository.TypeDetailRepository;
import ua.service.FileWriter;
import ua.service.UserService;
import ua.service.FileWriter.Folder;
import ua.service.ItemService;
import ua.service.specification.ItemSpecification;

@Service
public class ItemServiceImpl implements ItemService {
	

	@Autowired
	private ItemRepository repository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Autowired
	private TypeDetailRepository typeDetailRepository;
	
	@Autowired
	private NameDetailRepository nameDetailRepository;
	
	@Autowired
	private SpecificationRepository specificationRepository;
	
	@Autowired
	private DescriptionRepository decriptionRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileWriter fileWriter;

	@Override
	@Transactional(readOnly=true)
	public ItemForm findOne(int id) {
		Item entity = repository.findOne(id);
		ItemForm itemForm = new ItemForm();
		itemForm.setId(entity.getId());
		itemForm.setTypeDetail(entity.getTypeDetail());
		itemForm.setNameDetail(entity.getNameDetail());
		itemForm.setProducer(entity.getProducer());
		itemForm.setSpecification(entity.getSpecification());
		itemForm.setPrice(String.valueOf(entity.getPrice()));
		itemForm.setDescription(entity.getDescription());
		itemForm.setVersion(String.valueOf(entity.getVersion()));
		return itemForm;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Item> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public void save(ItemForm itemForm) {
		Item item = new Item();
		item.setId(itemForm.getId());
		item.setNameDetail(itemForm.getNameDetail());
		item.setProducer(itemForm.getProducer());
		item.setSpecification(itemForm.getSpecification());
		item.setTypeDetail(itemForm.getTypeDetail());
		item.setDescription(itemForm.getDescription());
		/*item.setVersion(Integer.valueOf(itemForm.getVersion()));*/
		item.setPrice(new BigDecimal(itemForm.getPrice().replace(',', '.')));
		item = repository.saveAndFlush(item);
		if(fileWriter.write(Folder.ITEM, itemForm.getFile(), item.getId())){
			if(item.getVersion()==null)item.setVersion(0);
			else item.setVersion(item.getVersion()+1);
		}
		repository.save(item);
	}

	@Override
	public void delete(int id) {
		repository.delete(id);
	}

	@Override
	public Page<Item> findAll(Pageable pageable, ItemFilter filter) {
		Page<Item> items = repository.findAll(new ItemSpecification(filter), pageable);
		return items;
	}
	@Override
	public List<Item> findByUserId(Principal principal) {
		return repository.findByUserId(userService.getUserId(principal));
	}

	@Override
	@Transactional
	public void addUsers(int id, Principal principal) {
	Item item = repository.findOne(id);
	 String name = principal.getName();
     User user = userService.findByUsername(name);
     item.getUsers().add(user);
	}

}
