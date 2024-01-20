package com.thec.card;

import static org.junit.Assert.assertEquals;

import com.thec.card.domain.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRestTemplateTest {
	
	@Mock
    private RestTemplate restTemplate;
 
    @InjectMocks
    private Usuario usuario = new Usuario();
 
    @Test
    public void usuarioMocking() {
       
    	Usuario user = new Usuario(1L, 2L, "delectus aut autem", Boolean.TRUE);
        Mockito.when(restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos/1", Usuario.class))
       .thenReturn(new ResponseEntity<Usuario>(user, HttpStatus.OK));   
          
        Usuario u = this.usuario.getUsuario();
    	
        assertEquals(user, u);
    }
}