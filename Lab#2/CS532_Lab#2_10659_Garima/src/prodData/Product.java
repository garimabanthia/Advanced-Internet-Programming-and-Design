package prodData;

import java.text.NumberFormat;

public class Product {
	private ProductCode code;
	private String name;
	private double mfgPrice; /* price paid to manufacturer */
	private double salePrice; /* price we sell the product for */

	public Product(ProductCode newCode, String newName) {
		code = newCode;
		name = newName;
	}

	public String getName() {
		return name;
	}

	public double getMfgPrice() {
		return mfgPrice;
	}

	public void setMfgPrice(double mfgPrice) {
		this.mfgPrice = mfgPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public ProductCode getCode() {
		return code;
	}

	public boolean equals(Object prod) {
		Product tstProd;
		if (!(prod instanceof Product)) {
			return false;
		}

		tstProd = (Product) prod;
		if ((this.name.equals(tstProd.name)) && (this.code == (tstProd.code))
				&& (this.mfgPrice == (tstProd.mfgPrice))
				&& (this.salePrice == (tstProd.salePrice))) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		return Integer.parseInt(this.mfgPrice * this.salePrice + " ");
	}

	public String toString() {
		String prodStr, mfgPriceStr, salePriceStr;
		NumberFormat dlrFormatter = NumberFormat.getCurrencyInstance();

		mfgPriceStr = dlrFormatter.format(mfgPrice);
		salePriceStr = dlrFormatter.format(salePrice);

		prodStr = "Product: " + name + "     Code: " + code + '\n';
		prodStr = prodStr + '\t' + "Mfg Price: " + mfgPriceStr
				+ "    Sale Price: " + salePriceStr + '\n';

		return prodStr;
	}
}
