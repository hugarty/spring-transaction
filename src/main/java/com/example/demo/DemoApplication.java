package com.example.demo;

import java.io.IOException;

import com.example.demo.model.Tabela;
import com.example.demo.model.service.DeleteService;
import com.example.demo.model.service.FindAndChangeService;
import com.example.demo.model.service.FindService;
import com.example.demo.model.service.SaveService;
import com.example.demo.model.service.UpdateService;
import com.example.demo.model.service.innertransaction.InnerTransactionFindAndChangeService;
import com.example.demo.model.service.outertransaction.OuterTransactionalUpdateService;
import com.example.demo.model.service.outertransaction.OuterDefaultUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackageClasses = UpdateService.class)
@EnableJpaRepositories
@EntityScan(basePackageClasses = Tabela.class)
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private FindAndChangeService findAndChangeNameService;
	@Autowired
	private FindService findService;
	@Autowired
	private DeleteService deleteService;
	@Autowired
	private UpdateService updateService;
	@Autowired
	private SaveService saveService;
	@Autowired
	private OuterTransactionalUpdateService outerTransactionService;
	@Autowired
	private OuterDefaultUpdateService outerDefaultService;
	@Autowired
	private InnerTransactionFindAndChangeService innerTransactionService;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n - - RUN - - \n");
		
		// saveService
		saveService.saveDefault();		// Salva
		saveService.saveTransactional();		// Salva
		// saveService.saveReadOnly();			// Lança Exceção
		saveService.savePropagationNever();	// Salva
		saveService.savePropagationNotSupported();	// Salva

		// findAndChangeNameService
		findAndChangeNameService.changeNameDefault(); 		// Não altera
		findAndChangeNameService.changeNameTransacional(); 	// Altera
		findAndChangeNameService.changeNameReadOnly();		// Não altera
		findAndChangeNameService.changeNamePropagationNotSupported();	// Não altera
		findAndChangeNameService.changeNamePropagationNever();	// Não altera
		
		// findService
		findService.findDefault();		// Recuperou
		findService.findTransactional();	// Recuperou
		findService.findReadOnly();		// Recuperou
		findService.findPropagationNotSupported();	// Recuperou
		findService.findPropagationNever();	// Recuperou

		// // updateService
		updateService.updateDefault();		// Altera
		updateService.updateTransactional();	// Altera
		updateService.updateReadOnly();		// Não altera
		updateService.updatePropagationNotSupported();	// Altera
		updateService.updatePropagationNever();		// Altera

		// // innerTransactionService
		innerTransactionService.updateFindReadOnly();		// Altera
		innerTransactionService.updateFindPropagationNotSupported();	// Altera
		innerTransactionService.updateFindPropagationNever();	// Altera

		// outerTransactionService
		outerTransactionService.updateReadOnly();		// Altera
		outerTransactionService.updatePropagationNotSupported();	// Não altera
		// outerTransactionService.updatePropagationNever();	// Lança exceção

		// outerDefaultService
		outerDefaultService.updateReadOnly();	// Não altera
		outerDefaultService.updatePropagationNotSupported();	// Não altera
		outerDefaultService.updatePropagationNever();		// Não altera

		// deleteService
		deleteService.deleteDefault();		// Deleta
		deleteService.deleteTransactional();	// Deleta
		deleteService.deleteReadOnly();		// Não deleta
		deleteService.deleteNotSupported();	// Deleta
		deleteService.deleteNever();		// Deleta

		System.out.println("\n - - END - - \n");
	}

}
