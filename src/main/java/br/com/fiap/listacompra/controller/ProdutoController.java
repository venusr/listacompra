package br.com.fiap.listacompra.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.listacompra.component.LoginComponent;
import br.com.fiap.listacompra.component.ProdutoComponent;
import br.com.fiap.listacompra.model.Login;
import br.com.fiap.listacompra.model.Produto;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/produto")
public class ProdutoController {

   @Autowired
   private ProdutoComponent produtoComponent;

   @Autowired
   private LoginComponent loginComponent;
   
   
   @GetMapping
   private List<Produto> findAll() {
       return produtoComponent.findAll();
   }
   
   //@GetMapping(value = "/nome/{nome}")
   //private Produto buscarProduto(@PathVariable(value = "nome") String nome) {
   //    return produtoComponent.buscarProduto(nome);
   //}

      
   @PostMapping
   private void save(@RequestBody Produto produto) {

		//validar se cliente existe ou não
		Login login = loginComponent.buscarId(produto.getLogin().getId());
		System.out.println(login);
		
		if (login == null) {
       	    System.out.println("está vazio classe login");
		}
		else {
       	System.out.println("tem classe login");
        produtoComponent.salvar(produto, login);
		}
   }
   
   @DeleteMapping
   private void deleteAll() {
       produtoComponent.deleteAll();
   }

   @DeleteMapping(value = "/nome/{nome}/{idLogin}")
   private void delete(@PathVariable(value = "nome") String nome, @PathVariable(value = "idLogin" ) String id) {
   	    Login login = loginComponent.buscarId(id);
  		System.out.println(login);

       produtoComponent.delete(nome, login);
   }
  
   @GetMapping(value = "/produtos/{idLogin}")
   private List<Produto> findByLogin(@PathVariable(value = "idLogin") String idLogin) {

		//validar se cliente existe ou não
		Login login = loginComponent.buscarId(idLogin);
		System.out.println(login);
		
		if (login == null) {
      	    System.out.println("está vazio classe login");
		}
	   
       return produtoComponent.findByLogin(login);
   }
   
   
   
}
