package treeApplication;

import java.util.Iterator;
import java.util.TreeSet;

import prodData.Product;
import readers.ProductFileReader;

public class TreeExample {
	public static void main(String args[]) {
		TreeSet<Product> prodSet;
		/* Create the data structure storing all our products */
		prodSet = readProdSet("prods.data");
		
		System.out.println("\n" + "The Product Set: ");
		printProdSet(prodSet);
	}
	
	private static TreeSet<Product> readProdSet(String fileName) {
		TreeSet<Product> prodSet;
		Product prod;
		ProductFileReader prodReader;
		
		
		prodReader = new ProductFileReader(fileName);
		prodReader.open();
		
        /* Add code here to Instantiate a TreeSet (prodset) */
		prodSet = new TreeSet<Product> ();
		

		prod = prodReader.nextProd();
		
		while (prod != null) {
            /* Add code here to Put the Product into your TreeSet */
			prodSet.add(prod);
			prod = prodReader.nextProd();
	
		}
		
		prodReader.close();
		return prodSet;
	}
	
	private static void printProdSet(TreeSet<Product> prodSet) {
		/*  Add code here to print the contents of the TreeSet  */
		Product prod;
		Iterator<Product> prodIterator;
		prodIterator= prodSet.iterator();
		
		
		while(prodIterator.hasNext()){
		prod=prodIterator.next();
		System.out.println(prod);  
		}
	}
}
