package com.ohjelmistoprojekti;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ohjelmistoprojekti.domain.Question;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(QuestionRepository qRepository) {
        return (args) -> {
            
            log.info("Save questions");
            qRepository.save(new Question("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?"));
            qRepository.save(new Question("Minkä merkkinen laite on?"));
            qRepository.save(new Question("Pystytkö käyttämään koulun vaatimia ohjelmistoja koneella?"));
            qRepository.save(new Question("Pystytkö käyttämään Peppiä laitteella ongelmitta?"));
            qRepository.save(new Question("Onko ohjeistus riittävä, jos käyttää muita kuin suositeltuja laitteita?"));
                    
            log.info("Show questions");
            for (Question question : qRepository.findAll()) {
                log.info(question.toString());
            }

        };
    }
}