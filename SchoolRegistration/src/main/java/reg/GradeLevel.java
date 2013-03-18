package reg;

public enum GradeLevel {
	K("K"),
	L1("1"),
	L2("2"),
	L3("3"),
	L4("4"),
	L5("5"),
	L6("6"),
	L7("7"),
	L8("8"),
	L9("9"),
	L10("10"),
	L11("11"),
	L12("12");
	
	private String displayValue;
	
	GradeLevel(String aDisplayValue) {
		displayValue = aDisplayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
	
	public boolean isInRange(GradeLevel min, GradeLevel max) {
		if(min == null || max == null ) {
			return false;
		}
		return ( (min.compareTo(this) <= 0) && (max.compareTo(this) >= 0) );
	}

}
