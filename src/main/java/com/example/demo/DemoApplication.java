package com.example.demo;

import java.io.IOException;

import com.example.demo.model.relacionamentomany.service.ManyService;
import com.example.demo.model.relacionamentoone.service.OneService;
import com.example.demo.model.tabela.Tabela;
import com.example.demo.model.tabela.dto.TabelaDto;
import com.example.demo.model.tabela.service.DeleteService;
import com.example.demo.model.tabela.service.FindAndChangeService;
import com.example.demo.model.tabela.service.FindService;
import com.example.demo.model.tabela.service.SaveService;
import com.example.demo.model.tabela.service.UpdateService;
import com.example.demo.model.tabela.service.innertransaction.InnerTransactionFindAndChangeService;
import com.example.demo.model.tabela.service.outertransaction.OuterDefaultUpdateService;
import com.example.demo.model.tabela.service.outertransaction.OuterTransactionalUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.demo.model")
@EnableJpaRepositories
@EntityScan(basePackages = "com.example.demo.model")
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

	@Autowired
	private OneService oneService;
	@Autowired
	private ManyService manyService;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n - - RUN - - \n");
		testTransactions();
		oneTestDto();
		manyTestDto();
		queryTestDto();
		System.out.println("\n - - END - - \n");
	}

	private void testTransactions() {
		System.out.println("\n - - testTransactions - - \n");
		
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


		// Esses são os únicos que alterão outras transações anotadas com @Transactional(readOnly=true)
		// // innerTransactionService
		innerTransactionService.updateFindReadOnly();		// Altera
		innerTransactionService.updateFindPropagationNotSupported();	// Altera
		innerTransactionService.updateFindPropagationNever();	// Altera
	
		// Esses são os únicos que alterão outras transações anotadas com @Transactional(readOnly=true)
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

	}


	// TODO mover esse método para abaixo do testTransactions
	private void oneTestDto() {
		System.out.println("\n - - oneTestDto - - \n");
		saveService.saveDefault();		// Salva
		oneService.saveNewRelacionamento();

		// findService
		Tabela findDefault = findService.findDefault();		// Recuperou
		Tabela findTransactional = findService.findTransactional();	// Recuperou
		Tabela findReadOnly = findService.findReadOnly();		// Recuperou
		Tabela findPropagationNotSupported = findService.findPropagationNotSupported();	// Recuperou
		Tabela findPropagationNever = findService.findPropagationNever();	// Recuperou

		// TabelaDto dtoDefault = TabelaDto.toDto(findDefault);					// Vai dar LazyInitializationException por conta da many to many
		// TabelaDto dtoTransactional = TabelaDto.toDto(findTransactional);		// Vai dar LazyInitializationException por conta da many to many
		// TabelaDto dtoReadOnly = TabelaDto.toDto(findReadOnly);				// Vai dar LazyInitializationException por conta da many to many
		// TabelaDto dtoPropagationNotSupported = TabelaDto.toDto(findPropagationNotSupported);	// Vai dar LazyInitializationException por conta da many to many
		// TabelaDto dtoPropagationNever = TabelaDto.toDto(findPropagationNever);	// Vai dar LazyInitializationException por conta da many to many

		oneService.deleteLast();
		deleteService.deleteDefault();
	}

	// TODO mover esse método para abaixo do testTransactions
	private void manyTestDto() {
		System.out.println("\n - - manyTestDto - - \n");
		saveService.saveDefault();
		manyService.saveNewRelacionamento();

		// findService
		Tabela tabelaDefault = findService.findDefault();		// Recupera Many como persistenteSet
		Tabela findTransactional = findService.findTransactional();	// Recupera Many como persistenteSet
		Tabela findReadOnly = findService.findReadOnly();		// Recupera Many como persistenteSet
		Tabela findPropagationNotSupported = findService.findPropagationNotSupported();	// Recupera Many como persistenteSet
		Tabela findPropagationNever = findService.findPropagationNever();	// Recupera Many como persistenteSet

		// TabelaDto dtoTransactional = TabelaDto.toDto(findTransactional);	// Lança exceção org.hibernate.LazyInitializationException
		// TabelaDto dtoReadOnly = TabelaDto.toDto(findReadOnly);	// Lança exceção org.hibernate.LazyInitializationException
		// TabelaDto dtoPropagationNotSupported = TabelaDto.toDto(findPropagationNotSupported);	// Lança exceção org.hibernate.LazyInitializationException
		// TabelaDto dtoPropagationNever = TabelaDto.toDto(findPropagationNever);	// Lança exceção org.hibernate.LazyInitializationException

		manyService.deleteLast();
		deleteService.deleteDefault();
	}

	private void queryTestDto () {
		System.out.println("\n - - queryTestDto - - \n");
		Integer id = saveService.saveDefault();
		manyService.saveNewRelacionamento();
		manyService.saveNewRelacionamento();
		manyService.saveNewRelacionamento();

		// Tabela findQueryAnnotationLazy = findService.findQueryAnnotationLazy(id);
		Tabela findQueryAnnotationEager = findService.findQueryAnnotationEager();

		// TabelaDto dtoLazy = TabelaDto.toDto(findQueryAnnotationLazy); 	// Lança exceção org.hibernate.LazyInitializationException
		TabelaDto dtoEager = TabelaDto.toDto(findQueryAnnotationEager);	// 

		manyService.deleteLast();
		manyService.deleteLast();
		manyService.deleteLast();
		deleteService.deleteDefault();
	}
}
