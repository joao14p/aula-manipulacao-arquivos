package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter a folder path: ");
		String str = sc.nextLine();
		
		File path = new File(str);
		String sourceFolder = path.getParent();
		
		boolean sucess = new File(sourceFolder + "\\out").mkdir();
		String targetFileStr = sourceFolder + "\\out\\summary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(str))){
			
			String line = br.readLine();
			while (line != null) {
				
				String[] fields = line.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				
				list.add(new Product(name, price, quantity));
				
				line = br.readLine();
			}
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
				for (Product item : list) {
					bw.write(item.getName() + ", " + String.format("%.2f", item.total()));
					bw.newLine();
				}
				
				System.out.println(targetFileStr + "CREATED!");
				
				}catch (IOException e) {
					System.out.println("Error Writing file: " + e.getMessage());
					}
		}catch (IOException e) {
			System.out.println("Error Reading file: " + e.getMessage());
		}
		
		
		sc.close();
	}

}

