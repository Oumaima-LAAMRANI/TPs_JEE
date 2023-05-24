package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import ma.enset.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean
	CommandLineRunner start(IHospitalService hospitalService, PatientRepository patientRepository, RendezVousRepository rendezVousRepository,MedecinRepository medecinRepository){
		return arg ->{
			Stream.of("Oumaima","Nouhaila","Yassmina")
					.forEach(name->{
						Patient patient=new Patient();
						patient.setNom(name);
						patient.setMalade(false);
						patient.setDateNaissance(new Date());
						hospitalService.savePatient(patient);
					}
			);
			Stream.of("Salma","Saida","Sabir")
					.forEach(name->{
								Medecin medecin=new Medecin();
								medecin.setNom(name);
								medecin.setSpecialite(Math.random()>0.5? "Cardio":"Dentiste");
								medecin.setEmail(name+"@gmail.com");
								hospitalService.saveMedecin(medecin);
							}
					);
			Patient patient1=patientRepository.findById(1L).orElse(null);
			Patient patient=patientRepository.findPatientByNom("Oumaima");
			Medecin medecin=medecinRepository.findMedecinByNom("Sabir");

			RendezVous rendezVous=new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			hospitalService.saveRDV(rendezVous);

			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de la consultation .....");
			hospitalService.saveConsultation(consultation);

		};
	}

}
