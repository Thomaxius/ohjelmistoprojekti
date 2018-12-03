package hh.palvelinohjelmointi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohjelmointi.domain.Book;
import hh.palvelinohjelmointi.domain.BookRepository;
import hh.palvelinohjelmointi.domain.Category;
import hh.palvelinohjelmointi.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Test
    public void findByAuthorNameReturnsBook() {
    	log.info("Running findByAuthorNameReturnsBook test..");
        List<Book> books = bookRepository.findByAuthorName("Edgar Allan poe");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getIsbn()).isEqualTo("159494-1");
    }
    
    @Test
    public void createNewBook() {
    	log.info("Running createNewBook test");
    	Book book = new Book("Testbook", "Testauthor", "testisbn", 1999, 
    			new Category("testcategory"));
    			bookRepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
    
    
    //This probably is a bad thing because we rely on the fact that books can be successfully added..
    @Test
    public void deleteBook() {
    	log.info("Running deleteBook test");
    	Book book = new Book("Testbook", "Testauthor", "testisbn", 1999, 
    			categoryRepository.findByName("Kaunokirjallisuus").get(0));
    			bookRepository.save(book);
    	    	log.info("here");    			
    	List<Book> books = bookRepository.findByAuthorName("Testauthor");
    	log.info("books", books.get(0));
    	bookRepository.deleteById(books.get(0).getId());
    	List<Book> newBooks = bookRepository.findByAuthorName("Testauthor");
    	assertThat(newBooks.isEmpty());
    } 

    
    
}