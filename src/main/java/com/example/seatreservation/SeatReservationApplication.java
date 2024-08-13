package com.example.seatreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeatReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatReservationApplication.class, args);
    }

    /*@Bean
    CommandLineRunner commandLineRunner(SeatRepository seatRepository) {

        return args -> {
            seatRepository.save(new Seat(null, "Jam", new Date(), false, 1.2, "CSIS", "M"));
            seatRepository.save(new Seat(null, "Jen", new Date(), true, 3.2, "ACCT", "F"));
            seatRepository.save(new Seat(null, "Jok", new Date(), false, 1.0, "BIOL", "M"));
            seatRepository.save(new Seat(null, "Jos", new Date(), false, 4.2, "BUSN", "F"));

            seatRepository.findAll().forEach(p->{

                System.out.println(p.getName());
            });

        };
    }*/

}