package com.ohjelmistoprojekti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ohjelmistoprojekti.domain.Answer;
import com.ohjelmistoprojekti.domain.AnswerRepository;
import com.ohjelmistoprojekti.domain.Category;
import com.ohjelmistoprojekti.domain.CategoryRepository;
import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;

@SpringBootApplication
public class QuestionApplication {
	private static final Logger log = LoggerFactory.getLogger(QuestionApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }

    @Bean
    public CommandLineRunner Demo(QuestionRepository questionRepository, AnswerRepository answerRepository, CategoryRepository categoryrepository) {
        return (args) -> {
            
        	log.info("Save categories");
        	if (categoryrepository.findByCategoryName("Yleiset") == null) {
        		categoryrepository.save(new Category("Yleiset"));
        	}
        	
            log.info("Save questions");
            if (questionRepository.findByQuestionName("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?") == null) {
            	//We assume that if one sample question is not in the database, the rest are not either
            questionRepository.save(new Question("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?", "text", categoryrepository.findByCategoryName("Yleiset").get(0)));
            questionRepository.save(new Question("Minkä merkkinen laite on?", "text", categoryrepository.findByCategoryName("Yleiset").get(0)));
            questionRepository.save(new Question("Pystytkö käyttämään koulun vaatimia ohjelmistoja koneella?", "text", categoryrepository.findByCategoryName("Yleiset").get(0)));
            questionRepository.save(new Question("Pystytkö käyttämään Peppiä laitteella ongelmitta?", "text", categoryrepository.findByCategoryName("Yleiset").get(0)));
            questionRepository.save(new Question("Onko ohjeistus riittävä, jos käyttää muita kuin suositeltuja laitteita?", "text", categoryrepository.findByCategoryName("Yleiset").get(0)));
            answerRepository.save(new Answer("Kannettavaa tietokonetta", questionRepository.findByQuestionName("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?").get(0)));
            answerRepository.save(new Answer("Pöytätietokonetta", questionRepository.findByQuestionName("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?").get(0)));
            }
                    
            log.info("Show questions");
            for (Question question : questionRepository.findAll()) {
                log.info(question.toString());
            }

        };
    }
}