package ua.dto.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SpecificationFilter {
	
	private final static Pattern PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})|([0-9]{1,18})$");
	
	
	private String maxValue = "";
	
	private String minValue = "";
	
	private BigDecimal max;
	
	private BigDecimal min;

	private List<Integer> nameStringSpecificationIds = new ArrayList<>();

	public List<Integer> getNameStringSpecificationIds() {
		return nameStringSpecificationIds;
	}

	public void setNameStringSpecificationIds(
			List<Integer> nameStringSpecificationIds) {
		this.nameStringSpecificationIds = nameStringSpecificationIds;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		if(PATTERN.matcher(maxValue).matches())max = new BigDecimal(maxValue);
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		if(PATTERN.matcher(minValue).matches())min = new BigDecimal(minValue);
		this.minValue = minValue;
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


	
	
	

}
