package br.com.fiap.listacompra.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.listacompra.model.Login;
import br.com.fiap.listacompra.repository.LoginRepository;

@Component
public class LoginComponent {


	@Autowired
	LoginRepository loginRepository;
	
	public Login salvar(Login login) {
        List<Login> logins = loginRepository.findByUsuario(login.getUsuario());
        if (logins.size() > 0) {
            login.setUsuario(logins.get(0).getUsuario());
        }
        return loginRepository.save(login);
    }
	
    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    public void deleteAll() {
        loginRepository.deleteAll();
    }
    
    public void delete(String usuario) {
    	
    	List<Login> logins = loginRepository.findByUsuario(usuario);
    	if(!logins.isEmpty())
    		loginRepository.delete(logins.get(0));
    }
  
    public Login buscarUsuario(String usuario) {
        List<Login> logins = loginRepository.findByUsuario(usuario);
        if (logins.isEmpty()) {
            return new Login();
        } else {
            return logins.get(0);
        }
    }
    
    public Login buscarId(String id) {
    	System.out.println("entrou buscarId");
    	List<Login> logins = loginRepository.findById(id);
    	System.out.println("entrou findbyid");

    	if (logins.isEmpty()) {
        	System.out.println("está vazio");
            return null;
        } else {
        	System.out.println("acho login");
            return logins.get(0);
        }
    }
    
    public int validarSenha_antiga(String usuario, String senha) {
    	List<Login> logins = loginRepository.findByUsuario(usuario);
    	if (logins.isEmpty()) {
    		return 1; //Codigo de Erro para quando o Usuario não existe  
    	} else {
    		if (logins.get(0).getSenha().equals(senha)) {
    			return 0; //Senha validada com sucesso
    		} ;
    		return 2; //Codigo de Erro para quando a Senha está incorreta;
    	}	

    }
    
    public boolean validarSenha(String usuario, String senha) {
    	List<Login> logins = loginRepository.findByUsuario(usuario);
    	if (logins.isEmpty()) {
    		return false; //Codigo de Erro para quando o Usuario não existe  
    	} else {
    		if (logins.get(0).getSenha().equals(senha)) {
    			return true; //Senha validada com sucesso
    		} ;
    		return false; //Codigo de Erro para quando a Senha está incorreta;
    	}	

    }

    
}
