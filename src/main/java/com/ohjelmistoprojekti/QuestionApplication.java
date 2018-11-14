package com.ohjelmistoprojekti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ohjelmistoprojekti.domain.Answer;
import com.ohjelmistoprojekti.domain.AnswerRepository;
import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;

@SpringBootApplication
public class QuestionApplication {
	private static final Logger log = LoggerFactory.getLogger(QuestionApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }

    @Bean
    public CommandLineRunner Demo(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        return (args) -> {
            
            log.info("Save questions");
            //questionRepository.save(new Question("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?", "text"));
            //questionRepository.save(new Question("Minkä merkkinen laite on?", "text"));
            //questionRepository.save(new Question("Pystytkö käyttämään koulun vaatimia ohjelmistoja koneella?", "text"));
            //questionRepository.save(new Question("Pystytkö käyttämään Peppiä laitteella ongelmitta?", "text"));
            //questionRepository.save(new Question("Onko ohjeistus riittävä, jos käyttää muita kuin suositeltuja laitteita?", "text"));
            
            //answerRepository.save(new Answer("Kannettavaa tietokonetta", questionRepository.findByQuestionName("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?").get(0)));
            //answerRepository.save(new Answer("Pöytätietokonetta", questionRepository.findByQuestionName("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?").get(0)));
                        
            log.info("Show questions");
            for (Question question : questionRepository.findAll()) {
                log.info(question.toString());
            }

        };
    }
}