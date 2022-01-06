package com.example.demo;

import java.io.IOException;

import com.example.demo.model.Tabela;
import com.example.demo.model.service.DeleteService;
import com.example.demo.model.service.FindAndChangeNameService;
import com.example.demo.model.service.FindService;
import com.example.demo.model.service.SaveService;
import com.example.demo.model.service.UpdateService;

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
	private FindAndChangeNameService findAndChangeNameService;
	@Autowired
	private FindService findService;
	@Autowired
	private DeleteService deleteService;
	@Autowired
	private UpdateService updateService;
	@Autowired
	private SaveService saveService;
	

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n - - - \n");

		// findAndChangeNameService.changeNameTransacional();
		// findAndChangeNameService.changeNamePropagationNever();
		findAndChangeNameService.changeNameDefault();

		// findService.findDefault();
		// findService.findDefault();
		// findService.findReadOnly();
		// findService.findPropagationNotSupported();
		// findService.findPropagationNever();
		
		// saveService.saveDefault();
		// saveService.saveTransactional();
		// // saveService.saveReadOnly(); Lança exceção.
		// saveService.savePropagationNever();
		// saveService.savePropagationNotSupported();



		updateService.updateReadOnly();

		// deleteService.deleteNotSupported(); // todo testar
		


		//tabelaService.saveReadOnly(); //org.postgresql.util.PSQLException: ERROR: cannot execute INSERT in a read-only transaction

		// tabelaService.update();

		// tabelaService.update();
		// userService.updateReadOnly();
		// userService.updatePropagationNotSupported();
		// try {
		// 	userService.updatePropagationNever();
		// } catch (Exception e) {
		// 	System.out.println(" # Lançou Exceção - " + e.getClass().getSimpleName() + ": "+ e.getMessage());
		// }

		// userService.noUpdateReadOnly();
		// userService.noUpdatePropagationNotSupported();
		// userService.noUpdatePropagationNever();	

		// tabelaService.deleteReadOnly();

		// tabelaService.deleteNever();
		// tabelaService.deleteNotSupported();

		System.out.println("\n - - END - - \n");

		// TODO implementar dois métodos salvar que após a transação acabar deve ter um break point, 
		// quero olhar o log e ver qual dos dois salvou de fato no banco a alteração que fizemos

		// TODO implementar dois metodos delete para a transação acima


		// TODO Implementar um método que é transacional default chamando um outro método que é transactional read only, 
		// alterar o dado dentro do método transactional default e ver se ele sofre alteração no banco.

	}

}
