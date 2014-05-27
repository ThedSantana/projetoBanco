package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.banco.dao.EnderecoDAO;
import br.com.banco.model.Endereco;

@Controller 
public class EnderecoController {


	private EnderecoDAO enderecoDao;
	
	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("idEndereco") Integer idEndereco, ModelMap modelMap) {
		modelMap.addAttribute("endereco", enderecoDao.find(idEndereco));
		return "contato/show";
	}	

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String list(ModelMap modelMap) {
		modelMap.addAttribute("enderecos",enderecoDao.listarEndereco());
		return "views/listar";
	}
	


	@RequestMapping(value = "/contato/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer idEndereco) {
		enderecoDao.remove(enderecoDao.find(idEndereco));
		return "redirect:/endereco";
	}

	@RequestMapping(value = "/endereco", method = RequestMethod.GET)
	public String form(ModelMap modelMap) {
		modelMap.addAttribute("endereco", new Endereco());
		return "endereco/create";
	}

	@RequestMapping(value = "/endereco", method = RequestMethod.POST)
	public String create(@ModelAttribute("endereco") Endereco endereco ) {
		enderecoDao.persist(endereco);
		return "redirect:/endereco";
	}

	@RequestMapping(value = "/endereco/{id}/form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("idEndereco") Integer idEndereco, ModelMap modelMap) {
		modelMap.addAttribute("endereco", enderecoDao.find(idEndereco));
		return "endereco/update";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@ModelAttribute("endereco") Endereco endereco) {
		enderecoDao.merge(endereco);
		return "redirect:/endereco";
	} 
	

/*	
	@RequestMapping(value="/listar")
	public ModelAndView listarEnderecos() {
		ModelAndView modelAndView = new ModelAndView("listar");
		
		List<Endereco> enderecos = enderecoDao.listarEndereco();
		modelAndView.addObject("enderecos",enderecos);
		return modelAndView;
	}
*/

}