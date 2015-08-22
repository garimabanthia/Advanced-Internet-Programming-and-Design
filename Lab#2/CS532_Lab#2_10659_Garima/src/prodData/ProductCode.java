package prodData;

public class ProductCode {
	private char cntryOfOrigin;
	private int deptCode;
	private String mfgCode;

	public ProductCode(char origin, int dept, String mfg) {
		cntryOfOrigin = origin;
		deptCode = dept;
		mfgCode = mfg;
	}

	public char getCntryOfOrigin() {
		return cntryOfOrigin;
	}

	public int getDeptCode() {
		return deptCode;
	}

	public String getMfgCode() {
		return mfgCode;
	}

	public String toString() {
		return cntryOfOrigin + "-" + deptCode + "-" + mfgCode;
	}

	public boolean equals(Object obj) {
		ProductCode prodCode;

		if (!(obj instanceof ProductCode)) {
			return false;
		}
		prodCode = (ProductCode) obj;
		if ((this.cntryOfOrigin == (prodCode.cntryOfOrigin))
				&& (this.deptCode == (prodCode.deptCode))
				&& (this.mfgCode.equals(prodCode.mfgCode))) {
			return true;
		}

		return false;

	}
	
	public int hashCode() {
		int totalintvalue=0;
		String totalvalue=getMfgCode()+getDeptCode();

		for(int i=0;i<totalvalue.length();i++){
			if(Character.isDigit(totalvalue.charAt(i))){
				totalintvalue+=Integer.parseInt(totalvalue.charAt(i)+"");
			}else{
				totalintvalue+=Integer.parseInt(Character.getNumericValue(totalvalue.charAt(i))+"");
			}
		}

		return totalintvalue;
		
		
		
		
	}

}
