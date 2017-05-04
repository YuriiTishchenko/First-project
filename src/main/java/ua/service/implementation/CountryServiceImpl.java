package ua.service.implementation;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.BasicFilter;
import ua.entity.Country;
import ua.entity.Producer;
import ua.repository.CountryRepository;
import ua.service.CountryService;
import ua.service.specification.CountrySpecification;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	@Transactional(readOnly=true)
	public Country findOne(int id) {
		return countryRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public void save(Country country) {
		countryRepository.save(country);		
	}

	@Override
	@Transactional
	public void delete(int id) {
		Country country = findOne(id);
		Hibernate.initialize(country.getProducers());
		for (Producer producer : country.getProducers()) {
			producer.setCountry(null);
		}
		countryRepository.delete(id);
	}

	@Override
	public Country findByName(String name) {
		return countryRepository.findByName(name);
	}
	
	@Override
	public Page<Country> findAll(Pageable pageable,BasicFilter filter) {
		return countryRepository.findAll(new CountrySpecification(filter), pageable);
	}

	@Override
	public void countryCount(int count) {
		
		
	}
}
