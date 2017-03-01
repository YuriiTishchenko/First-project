package ua.dto.filter;

import java.util.ArrayList;
import java.util.List;

public class ProducerFilter {
	
	private String search = "";
	
	private List<Integer> countryIds = new ArrayList<>();

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Integer> getCountryIds() {
		return countryIds;
	}

	public void setCountryIds(List<Integer> countryIds) {
		this.countryIds = countryIds;
	}
	
		
}
