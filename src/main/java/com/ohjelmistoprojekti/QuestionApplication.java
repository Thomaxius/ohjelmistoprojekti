package com.ohjelmistoprojekti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;

@SpringBootApplication
public class QuestionApplication {
	private static final Logger log = LoggerFactory.getLogger(QuestionApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(QuestionRepository questionRepository) {
        return (args) -> {
            
            log.info("Save questions");
            questionRepository.save(new Question("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?"));
            questionRepository.save(new Question("Minkä merkkinen laite on?"));
            questionRepository.save(new Question("Pystytkö käyttämään koulun vaatimia ohjelmistoja koneella?"));
            questionRepository.save(new Question("Pystytkö käyttämään Peppiä laitteella ongelmitta?"));
            questionRepository.save(new Question("Onko ohjeistus riittävä, jos käyttää muita kuin suositeltuja laitteita?"));
                    
            log.info("Show questions");
            for (Question question : questionRepository.findAll()) {
                log.info(question.toString());
            }

        };
    }
}