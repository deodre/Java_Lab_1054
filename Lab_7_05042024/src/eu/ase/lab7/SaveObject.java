package eu.ase.lab7;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveObject {

	public static void main(String[] args) {
		
		Author a1 = new Author("Franz Kafka", 1883);
		
		Book b1 = new Book("The Trial", a1, 1925);
		Book b2 = new Book("The Castle", a1, 1926);
		
		boolean stmt = (b1.getAuthor() == b2.getAuthor()) && (b1.getAuthor() == a1);

		System.out.println(stmt);
		
		try {
			FileOutputStream fos = new FileOutputStream("books.bin");
			
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			System.out.println("Saving books to file...");
			
			oos.writeObject(b1);
			oos.writeObject(b2);
			oos.writeObject(a1);

			oos.close();
			
			System.out.println("Done");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
