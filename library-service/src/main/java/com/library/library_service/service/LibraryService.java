package com.library.library_service.service;

import com.library.library_service.client.BookServiceClient;
import com.library.library_service.dto.AddBookRequest;
import com.library.library_service.dto.LibraryDto;
import com.library.library_service.exception.LibraryNotFoundException;
import com.library.library_service.model.Library;
import com.library.library_service.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LibraryService {

    private LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibraryById(String id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found with Id: " + id));
        LibraryDto libraryDto = new LibraryDto(library.getId(), library.getUserBook()
                .stream()
                .map(book -> bookServiceClient.getBookById(book).getBody())
                .collect(Collectors.toList()));
        return libraryDto;
    }

    public LibraryDto createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }

    public void addBookToLibrary(AddBookRequest request) {
        String bookId = bookServiceClient.getBookByIsbn(request.getIsbn()).getBody().getBookId();

        Library library = libraryRepository.findById(request.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found with Id: " + request.getId()));

        library.getUserBook().add(bookId);

        libraryRepository.save(library);
    }
}
