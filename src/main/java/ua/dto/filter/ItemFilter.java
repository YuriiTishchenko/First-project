package ua.dto.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ItemFilter {

	private final static Pattern PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})|([0-9]{1,18})$");

	
	private String maxPrice = "";
	
	private String minPrice = "";
	
	private BigDecimal max;
	
	private BigDecimal min;
	
	private List<Integer> producerIds = new ArrayList<>();
	
	private List<Integer> typeDetailIds = new ArrayList<>();
	
	private List<Integer> nameDetailIds = new ArrayList<>();
	
	
	

	

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		if(PATTERN.matcher(maxPrice).matches())max = new BigDecimal(maxPrice);
		this.maxPrice = maxPrice;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		if(PATTERN.matcher(minPrice).matches())min = new BigDecimal(minPrice);
		this.minPrice = minPrice;
	}



	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}


	public List<Integer> getProducerIds() {
		return producerIds;
	}


	public List<Integer> getTypeDetailIds() {
		return typeDetailIds;
	}


	public List<Integer> getNameDetailIds() {
		return nameDetailIds;
	}


	public void setProducerIds(List<Integer> producerIds) {
		this.producerIds = producerIds;
	}


	public void setTypeDetailIds(List<Integer> typeDetailIds) {
		this.typeDetailIds = typeDetailIds;
	}


	public void setNameDetailIds(List<Integer> nameDetailIds) {
		this.nameDetailIds = nameDetailIds;
	}



}