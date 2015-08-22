package mapApplication;

import java.util.*;

import prodData.Product;
import prodData.ProductCode;
import readers.ProductFileReader;

public class MapExample {
	public static void main(String args[]) {
		LinkedHashMap<ProductCode, Product> prodMap;
		ProductCode prodCode;
		Product prod=null;
		
		/* Create the data structure storing all our products */
		prodMap = readProdMap("prods.data");
		
		if (args.length == 3) {
			prodCode = buildProdCode(args);
            /* Is prodCode in the Map?? If so, store the Product in prod */
			//prod = /* ? */;
			if (prodMap.containsKey(prodCode)){
				prod=  prodMap.get(prodCode);
			}			
			System.out.println("*** Result of Product Lookup  ***");
			if (prod ==   null) {
				System.out.println("Product code: " + prodCode + " not found");
			} else {
				System.out.println(prod);
			}
		}
		
		System.out.println("\n" + "The Product Map: ");
		printProdMap(prodMap);
	}
	
	private static ProductCode buildProdCode(String prodCodePieces[]) {
		char ctryCode;
		String ctryCodeStr;
		int deptCode;
		String mfgCode;
		ProductCode prodCode;
		
		ctryCodeStr = prodCodePieces[0];
		ctryCode = ctryCodeStr.charAt(0);
		
		deptCode = Integer.parseInt(prodCodePieces[1]);
		mfgCode = prodCodePieces[2];
		
		prodCode = new ProductCode(ctryCode, deptCode, mfgCode);
		return prodCode;
	}
	
	private static LinkedHashMap<ProductCode, Product> readProdMap(String fileName) {
		LinkedHashMap<ProductCode, Product> prodMap;
		Product prod;
		ProductCode prodCode;
		ProductFileReader prodReader;
		
		prodReader = new ProductFileReader(fileName);
		prodReader.open();
		
		
		/* Add code here to Instantiate a new Map */
		/* prodMap = ? */
		prodMap = new LinkedHashMap<ProductCode, Product>();
			
		prod = prodReader.nextProd();
		while (prod != null) {
			/*  Add code here to add the Product to the Map  */
			prodCode = prod.getCode();
			prodMap.put(prodCode , prod);
				
			prod = prodReader.nextProd();
		}
		
		prodReader.close();
		return prodMap;
	}
	
	/*  Walk through the map and print each element  */
	private static void printProdMap(LinkedHashMap<ProductCode, Product> prodMap) {
		Set<ProductCode> prodKeys;
		prodKeys= prodMap.keySet();
		Iterator<ProductCode> prodIterator;
		Product objProd;
		ProductCode objCode;
		
		for (prodIterator =prodKeys.iterator();prodIterator.hasNext();){
			objCode= prodIterator.next();
			objProd=prodMap.get(objCode);
			System.out.println(objProd);
			
			
		}
		
		
	}

}
