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
import com.ohjelmistoprojekti.domain.User;
import com.ohjelmistoprojekti.domain.UserRepository;


@SpringBootApplication
public class QuestionApplication {
	private static final Logger log = LoggerFactory.getLogger(QuestionApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }

    @Bean
    public CommandLineRunner Demo(UserRepository uRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, CategoryRepository categoryrepository) {
        return (args) -> {
            
        	log.info("Save categories");
        	if (categoryrepository.findByCategoryName("kategoria1").isEmpty()) {
        		categoryrepository.save(new Category("kategoria1"));
        		categoryrepository.save(new Category("kategoria2"));
        		categoryrepository.save(new Category("kategoria3"));
        		categoryrepository.save(new Category("kategoria4"));
        	}
        	
			User user1 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			uRepository.save(user1);
			
			
            log.info("Save questions");
            if (questionRepository.findByQuestionName("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?").isEmpty()) {
            	//We assume that if one sample question is not in the database, the rest are not either
            questionRepository.save(new Question("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?", "checkbox", categoryrepository.findByCategoryName("kategoria1").get(0), new String[] {"Kannettava tietokone","Kannettava tietokone VDI:n yli","Koulun kone",
            		"Koulun kone VDI:n yli", "Pöytäkone", "Tabletti", "Puhelin"}));
            questionRepository.save(new Question("Millä käyttöjärjestelmillä pääasiassa käytät koulun ohjelmia/työvälineitä?", "checkbox", categoryrepository.findByCategoryName("kategoria1").get(0), new String[] {"Windows", "Linux", "IOS", "Android", "joku muu"}));
            questionRepository.save(new Question("Millä selaimella  pääasiassa käytät koulun ohjelmia/työvälineitä?", "checkbox", categoryrepository.findByCategoryName("kategoria1").get(0), new String[] {"Google Chrome","Mozilla Firefox","Safari","Internet Explorer","joku muu"}));
            questionRepository.save(new Question("Koetko pystyväsi käyttämään koulun vaatimia ohjelmistoja hyvin laitteesi kautta?", "radio", categoryrepository.findByCategoryName("kategoria1").get(0), new String[] {"Kyllä", "En", "En osaa sanoa"}));
            
            questionRepository.save(new Question("Oletko yleisesti tyytyväinen koulun tarjoamiin ohjelmiin/työvälineisiin?", "radio", categoryrepository.findByCategoryName("kategoria2").get(0), new String[] {"Kyllä", "En", "En osaa sanoa"}));
            questionRepository.save(new Question("Mitä alla olevista koulun työvälineistä tai ohjelmista käytät tai olet käyttänyt?", "radio", categoryrepository.findByCategoryName("kategoria2").get(0), new String[] {"Office o365 - Sähköposti","Office o365 - One Drive","Peppi","Asio","Lukkarikone","Skype for Business"}));
            questionRepository.save(new Question("Oletko törmännyt ongelmiin käyttäessäsi koulun työvälineitä tai ohjelmia?", "radio", categoryrepository.findByCategoryName("kategoria2").get(0), new String[] {"Kyllä", "En", "En osaa sanoa"}));
            questionRepository.save(new Question("Jos olet, mitä ohjelmaa tai työvälinettä ongelma koskee?", "checkbox", categoryrepository.findByCategoryName("kategoria2").get(0), new String[] {"Office o365", "Moodle", "Peppi", "VDI", "Asio", "Lukkarikone", "Vanha MyNet", "Skype for Business", "Jokin muu"}));
            questionRepository.save(new Question("Jos olet törmännyt ongelmiin työvälineiden ja ohjelmien kanssa, koitko että ongelman takia kurssin suorittaminen hankaloitui tai ongelma vaikutti kurssin tai työn suorittamiseen kriittisesti?", "checkbox", categoryrepository.findByCategoryName("kategoria2").get(0), new String[] {"Kyllä", "En", "En osaa sanoa"}));
            questionRepository.save(new Question("Oletko joutunut käyttämään jotain korvaavaa ohjelmaa koulun suositellun ohjelman sijasta?", "radio", categoryrepository.findByCategoryName("kategoria2").get(0), new String[] {"Kyllä", "En", "En osaa sanoa"}));
            questionRepository.save(new Question("Jos olet, mitä korvaavaa ohjelmaa olet käyttänyt?", "text", categoryrepository.findByCategoryName("kategoria2").get(0)));

            questionRepository.save(new Question("Oletko tyytyväinen koulun tarjoamiin ryhmätyöskentelyn työvälineisiin ja ohjelmiin?", "radio", categoryrepository.findByCategoryName("kategoria3").get(0), new String[] {"Kyllä", "En", "En osaa sanoa"}));
            questionRepository.save(new Question("Haluaisitko koulun tarjoavan laajemmin ryhmätyökaluja kursseilla?", "radio", categoryrepository.findByCategoryName("kategoria3").get(0), new String[] {"Kyllä", "En", "En osaa sanoa"}));
            questionRepository.save(new Question("Mitä ohjelmia ja työvälineitä olet käyttänyt ryhmätyöskentelyssä tähän mennessä?", "checkbox", categoryrepository.findByCategoryName("kategoria3").get(0), new String[] {"Whatsapp", "Slack", "Trello", "o365 - OneDrive", "GitHub", "Discord", "Jokin muu" }));
            questionRepository.save(new Question("Jos valitsit edellisessä kysymyksessä kohdan jokin muu, mihin ohjelmaan tai työkaluun viittaisit?", "text", categoryrepository.findByCategoryName("kategoria3").get(0)));
            
            questionRepository.save(new Question("Koetko että puhelimella työvälineiden ja ohjelmien käyttö on helppoa?", "radio", categoryrepository.findByCategoryName("kategoria4").get(0), new String[] {"Kyllä", "En", "En osaa sanoa"}));
            questionRepository.save(new Question("Koetko että puhelimen kautta osa ohjelmien ja työvälineiden toiminnallisuuksista katoaa tai toimii puutteellisesti? ", "radio", categoryrepository.findByCategoryName("kategoria4").get(0), new String[] {"Kyllä", "En", "En osaa sanoa"}));
            questionRepository.save(new Question("Jos vastasit kyllä, mitä ohjelmaa käytettäessä tämä tulee esille?", "checkbox", categoryrepository.findByCategoryName("kategoria4").get(0), new String[] {"Office o365", "Moodle", "Peppi", "VDI", "Asio", "Lukkarikone", "Vanha MyNet", "Skype For Business", "Jokin muu"}));
            questionRepository.save(new Question("Jos vastasit yllä olevaan kysymykseen, mitä ominaisuutta nämä ongelmat yleensä koskevat?", "text", categoryrepository.findByCategoryName("kategoria4").get(0), new String[] {"Responsiivisuutta", "Tiedon katoamista", "Linkkien supistamista/kadottamista", "Asemointia", "Jotain muuta"}));           
            questionRepository.save(new Question("Kerro lyhyesti oma mielipiteesi mitä parannettavaa koulun työvälineiden ja ohjelmien tarjonnassa olisi?", "text", categoryrepository.findByCategoryName("kategoria4").get(0)));
            
            //answerRepository.save(new Answer("kek", questionRepository.findByQuestionName("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?").get(0)));
            
            answerRepository.save(new Answer("Kek", questionRepository.findByQuestionName("Mitä laitetta käytät pääasiassa koulutehtävien tekemiseen?").get(0)));
            }
                               
            log.info("Show questions");
            for (Question question : questionRepository.findAll()) {
                log.info(question.toString());
            }

        };
    }
}