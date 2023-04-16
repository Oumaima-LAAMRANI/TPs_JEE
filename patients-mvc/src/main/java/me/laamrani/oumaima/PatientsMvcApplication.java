package me.laamrani.oumaima;

import me.laamrani.oumaima.entities.Patient;
import me.laamrani.oumaima.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.crypto.Data;
import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args ->{
			patientRepository.save(new Patient(null, "Oumaima",new Date(), false, 120));
			patientRepository.save(new Patient(null, "Tasnim",new Date(), true, 300));
			patientRepository.save(new Patient(null, "Rabiaa",new Date(), false, 500));
			patientRepository.save(new Patient(null, "Fadwa",new Date(), true, 210));
			patientRepository.save(new Patient(null, "Mourad",new Date(), false, 170));
			patientRepository.findAll().forEach(p->{
						System.out.println(p.getNom());
					}
			);

		};
	}
}
