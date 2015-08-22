package prodData;

public class ProductCode implements Comparable<ProductCode> {
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

	@Override
	public int compareTo(ProductCode cmpCode) {
		if (((this.mfgCode.compareTo(cmpCode.mfgCode)) == -1)
				&& ((this.cntryOfOrigin == cmpCode.cntryOfOrigin))
				&& ((this.deptCode == cmpCode.deptCode))) {
			return -1;

		}
		if (this.mfgCode.compareTo(cmpCode.mfgCode) == 0
				&& this.deptCode < cmpCode.deptCode
				&& this.cntryOfOrigin < cmpCode.cntryOfOrigin) {
			return 0;
		}
		return 1;
	}

}
