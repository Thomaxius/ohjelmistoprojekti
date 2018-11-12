package com.ohjelmistoprojekti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ohjelmistoprojekti.domain.Category;
import com.ohjelmistoprojekti.domain.CategoryRepository;
import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;
import com.ohjelmistoprojekti.domain.UserRepository;


@SpringBootApplication
public class QuestionApplication {
	private static final Logger log = LoggerFactory.getLogger(QuestionApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(QuestionRepository QuestionRepository, CategoryRepository categoryRepository, UserRepository urepository) {
        return (args) -> {
            
        	//User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@us.er","USER");
			//User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@adm.in", "ADMIN");
			//urepository.save(user1);
			//urepository.save(user2);
			
        	//categoryRepository.save(new Category("Biographies"));
			//categoryRepository.save(new Category("Fantasy"));
			
			
            //log.info("Save questions");

            //QuestionRepository.save(new Question("Minkä merkkinen laite on?", categoryRepository.findByCategoryName("Fantasy").get(0)));
            //QuestionRepository.save(new Question("Pystytkö käyttämään koulun vaatimia ohjelmistoja koneella?", categoryRepository.findByCategoryName("Fantasy").get(0)));
            //QuestionRepository.save(new Question("Pystytkö käyttämään Peppiä laitteella ongelmitta?", categoryRepository.findByCategoryName("Fantasy").get(0)));
           // QuestionRepository.save(new Question("Onko ohjeistus riittävä, jos käyttää muita kuin suositeltuja laitteita?", categoryRepository.findByCategoryName("Fantasy").get(0)));
           // log.info("Show questions");
           // for (Question Question : QuestionRepository.findAll()) {
            //    log.info(Question.toString());
            //}

        };
    }
}