package prodData;
import java.text.NumberFormat;

public class Product implements Comparable<Product> {
	private ProductCode code;
	private String name;  
	private double mfgPrice;   /* price paid to manufacturer */
	private double salePrice;  /* price we sell the product for */
	
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
	
	public String toString() {
		String prodStr, mfgPriceStr, salePriceStr;
		NumberFormat dlrFormatter = NumberFormat.getCurrencyInstance();
		
		mfgPriceStr = dlrFormatter.format(mfgPrice);
		salePriceStr = dlrFormatter.format(salePrice);
		
		prodStr = "Product: " + name + "     Code: " + code + '\n';
		prodStr = prodStr + '\t' + "Mfg Price: " + mfgPriceStr + "    Sale Price: " + salePriceStr + '\n';
		
		return prodStr;
	}

	@Override
	public int compareTo(Product cmpProd) {
		if(this.code.compareTo(cmpProd.code)==-1){
			return -1;	
		}if(this.code.compareTo(cmpProd.code)==0){
			return 0;
		}
			return 1;
	
	}
	

}
