package ua.service.implementation;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.ProducerFilter;
import ua.entity.Item;
import ua.entity.Producer;
import ua.repository.CountryRepository;
import ua.repository.ProducerRepository;
import ua.service.ProducerService;
import ua.service.specification.ProducerSpecification;
@Service
public class ProducerServiceImpl implements ProducerService {
	@Autowired
	private ProducerRepository producerRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Producer findOne(int id) {
		return producerRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Producer> findAll() {
		return producerRepository.findAll();
	}
	
	@Override
	public void save(Producer producer) {
		producerRepository.save(producer);
	}

	@Override
	@Transactional
	public void delete(int id) {
		Producer producer = findOne(id);
		Hibernate.initialize(producer.getItems());
		for (Item item : producer.getItems()) {
			item.setProducer(null);
		}	
		producerRepository.delete(id);
	}

	@Override
	public Producer findByName(String name) {
		return producerRepository.findByName(name);
	}

	@Override
	public Page<Producer> findAll(Pageable pageable, ProducerFilter filter) {
		return producerRepository.findAll(new ProducerSpecification(filter), pageable);
	}

	
}
