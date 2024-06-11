package com.library.book_service;

import com.library.book_service.model.Book;
import com.library.book_service.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BookServiceApplication implements CommandLineRunner {

	private final BookRepository bookRepository;

	public BookServiceApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book1 = new Book("Dünyanin Gözü", 2000, "RobertJordan", "Ithaki Yayinlari", "Tcsd4215A");
		Book book2 = new Book("Yüzüklerin Efendisi", 1960, "J.R.R Tolkien", "Metis Yayinlari", "Tcsd4234V");
		Book book3 = new Book("Harry Potter ve Felsefe Tasi", 1997, "J.K Rowling", "YK Yayinlari", "Tcsd42v2KB");

		List<Book>  bookList = bookRepository.saveAll(Arrays.asList(book1, book2, book3));

		System.out.println(bookList);
	}
}
