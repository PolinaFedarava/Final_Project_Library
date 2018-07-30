package by.htp.library.entity;

public class Book {
	
	private int id_book;
	private String name_book;
	private String author;
	private int quantity;
	private String publisher;
	
	
public Book() {
		super();
	}


@Override
public String toString() {
	return "Book [id_book=" + id_book + ", name_book=" + name_book + ", autor=" + author + ", quantity=" + quantity
			+ ", publisher=" + publisher + "]";
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((author == null) ? 0 : author.hashCode());
	result = prime * result + id_book;
	result = prime * result + ((name_book == null) ? 0 : name_book.hashCode());
	result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
	result = prime * result + quantity;
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Book other = (Book) obj;
	if (author == null) {
		if (other.author != null)
			return false;
	} else if (!author.equals(other.author))
		return false;
	if (id_book != other.id_book)
		return false;
	if (name_book == null) {
		if (other.name_book != null)
			return false;
	} else if (!name_book.equals(other.name_book))
		return false;
	if (publisher == null) {
		if (other.publisher != null)
			return false;
	} else if (!publisher.equals(other.publisher))
		return false;
	if (quantity != other.quantity)
		return false;
	return true;
}


public int getId_book() {
	return id_book;
}


public void setId_book(int id_book) {
	this.id_book = id_book;
}


public String getName_book() {
	return name_book;
}


public void setName_book(String name_book) {
	this.name_book = name_book;
}


public String getAutor() {
	return author;
}


public void setAutor(String autor) {
	this.author = autor;
}


public int getQuantity() {
	return quantity;
}


public void setQuantity(int quantity) {
	this.quantity = quantity;
}


public String getPublisher() {
	return publisher;
}


public void setPublisher(String publisher) {
	this.publisher = publisher;
}

 

}
