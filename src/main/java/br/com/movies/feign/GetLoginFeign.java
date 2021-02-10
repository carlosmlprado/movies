package br.com.movies.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.movies.dto.UsuarioDTO;

@FeignClient(value = "login", url = "http://localhost:8098/login")
public interface GetLoginFeign {
	
	@RequestMapping(method = RequestMethod.GET, value = "/getLogin/{cpf}/{name}", produces = "application/json")
	UsuarioDTO getLogin(@PathVariable("cpf") String cpf, @PathVariable("name") String name);
	
}
