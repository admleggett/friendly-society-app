package com.friendly.passbook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.friendly.passbook.entity.Account;
import com.friendly.passbook.repository.AccountRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class PassbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassbookApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadTestData(AccountRepository accountRepository) {
		return args -> {
			if (accountRepository.count() == 0) {
				Account testAccount = new Account();
				testAccount.setAccountNumber("1234567890");
				testAccount.setOwnerName("Test User");
				testAccount.setBalance(new BigDecimal("1000.00"));
				accountRepository.save(testAccount);
			}
		};
	}
}
