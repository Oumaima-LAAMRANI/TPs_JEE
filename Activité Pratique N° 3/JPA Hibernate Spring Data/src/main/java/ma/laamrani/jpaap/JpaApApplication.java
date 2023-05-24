package ma.laamrani.jpaap;

import ma.laamrani.jpaap.entities.Patient;
import ma.laamrani.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {
    @Autowired // pour l'implementation de l'interface
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i=0;i<100;i++) {
            patientRepository.save(new Patient(null, "Oumaima", new Date(), Math.random()>0.5 ? true: false, (int)(Math.random()*100)));
        }
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,5));
        System.out.println("Total pages : "+patients.getTotalPages());
        System.out.println("Total elements : "+patients.getTotalElements());
        System.out.println("Num page : "+ patients.getNumber());
        List<Patient> content = patients.getContent();
        List<Patient> byMalade = patientRepository.chercherPatients("%u%", 40);
        byMalade.forEach(p -> {
            System.out.println("==========================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.getScore());
            System.out.println(p.isMalade());
        });
        System.out.println("******************************************");

        Patient patient = patientRepository.findById(1L).orElse(null);
        if (patient != null) {
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        patient.setScore(604);
        //si l'id = null, il va faire insert, sinon il va faire update
        patientRepository.save(patient);
        //patientRepository.deleteById(1L);
    }
}
