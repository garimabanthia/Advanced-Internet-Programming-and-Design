package readers;
import java.util.ArrayList;

import prodData.Product;
import prodData.ProductCode;


/*  This class simulates reading Products from a file.  Currently our products are
 *  "hardcoded".  At a later time we could
 *  add the actual code needed to read from the file.
 */
public class ProductFileReader {
	private String fileName;
	private int prodIdx;
	private boolean isOpen = false;
	private ArrayList<Product> prodList;
	
	public ProductFileReader(String file) {
		fileName = file;
		prodIdx = 0;
		initProdList();
	}
	
	public int open() {
		isOpen = true;
		return 0;
	}
	
	public int close() {
		isOpen = false;
		return 0;
	}
	
	public Product nextProd() {
		Product prod;
		
		if (!isOpen) {
			return null;
		}
		
		if (prodIdx >= prodList.size()) {
			return null;
		}
		
		prod = prodList.get(prodIdx);
		prodIdx++;
		return prod;
	}
	
	private void initProdList() {
		Product prod;
		ProductCode code;
		
		prodList = new ArrayList<Product>();
		
		code = new ProductCode('U', 101, "A584321");
		prod = new Product(code, "toaster");
		prod.setMfgPrice(25.5);
		prod.setSalePrice(59.90);
		prodList.add(prod);
		
		code = new ProductCode('C', 101, "654-1A");
		prod = new Product(code, "microwave");
		prod.setMfgPrice(69.99);
		prod.setSalePrice(99.90);
		prodList.add(prod);
		
		code = new ProductCode('N', 1120, "C-102A-1111-26");
		prod = new Product(code, "tulip bulbs 12-pack");
		prod.setMfgPrice(2.0);
		prod.setSalePrice(9.50);
		prodList.add(prod);
		
		code = new ProductCode('I', 212, "B53H21");
		prod = new Product(code, "shovel");
		prod.setMfgPrice(9.0);
		prod.setSalePrice(17.50);
		prodList.add(prod);
		
		code = new ProductCode('I', 212, "B53H21");
		prod = new Product(code, "garden hose");
		prod.setMfgPrice(16.0);
		prod.setSalePrice(28.0);
		prodList.add(prod);
		
		code = new ProductCode('I', 212, "AG12");
		prod = new Product(code, "garden hose");
		prod.setMfgPrice(16.0);
		prod.setSalePrice(28.0);
		prodList.add(prod);
	}
}
