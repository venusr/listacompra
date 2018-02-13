package br.com.fiap.listacompra.component;

import org.springframework.stereotype.Component;

import br.com.fiap.listacompra.model.Login;
import br.com.fiap.listacompra.model.Produto;
import br.com.fiap.listacompra.repository.ProdutoRepository;
//import br.com.fiap.listacompra.model.Login;
//import br.com.fiap.listacompra.repository.LoginRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;

@Component
public class ProdutoComponent {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public Produto salvar(Produto produto, Login login) {
        List<Produto> produtosLogin = produtoRepository.findByLogin(login);
        
        if (produtosLogin.size() > 0) {
           for (int i=0; i < produtosLogin.size();i++) {
        	  System.out.println("produtoLogin " + produtosLogin.get(i).getNome());
        	  System.out.println("produto" + produto.getNome());
        	          	  
              if (produtosLogin.get(i).getNome().equals(produto.getNome())){
            	  System.out.println("Produtos sao iguais");
                  List<Produto> produtos = produtoRepository.findById(produtosLogin.get(i).getId());
            	  produto.setId(produtosLogin.get(i).getId());

              }
        	
           }
        }
        return produtoRepository.save(produto);
        
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public List<Produto> findByLogin(Login login) {
        return produtoRepository.findByLogin(login);
    }

    
    public void deleteAll() {
        produtoRepository.deleteAll();
    }
    
    public void delete(String nome, Login login) {
    	
        List<Produto> produtosLogin = produtoRepository.findByLogin(login);
        
        if (produtosLogin.size() > 0) {
           for (int i=0; i < produtosLogin.size();i++) {
        	  System.out.println("produtoLogin " + produtosLogin.get(i).getNome());
        	          	  
              if (produtosLogin.get(i).getNome().equals(nome)){
            	  System.out.println("Produtos sao iguais");
            	  produtoRepository.delete(produtosLogin.get(i));

              }
        	
           }
 			
 		}
 	   

        
        
//    	List<Produto> produtos = produtoRepository.findByNome(nome);
 //   	if(!produtos.isEmpty())
 //   		produtoRepository.delete(produtos.get(0));
    }

    public void update(String nome, Produto produto) {
   // 	List<Produto> produtos = produtoRepository.findByNome(nome);
   // 	if(!produtos.isEmpty())
   // 		produtoRepository.save(produtos.get(0));
    }

    
   // public Produto buscarProduto(String nome) {
    //    List<Produto> produtos = produtoRepository.findByNome(nome);
    //    if (produtos.isEmpty()) {
    //        return new Produto();
    //    } else {
    //        return produtos.get(0);
     //   }
    //}
	
}
