package br.com.projeto.filtros;
import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.br.projeto.controller.ControleLogin;
//filtra apenas o que estiver dentro da pasta privado
@WebFilter(urlPatterns = "/privado/*")
public class FiltroSeguranca implements Filter {

	public void destroy() {
		
		
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponser =  (HttpServletResponse) response;
		HttpSession sessao =  httpRequest.getSession();
		String contextPath = httpRequest.getContextPath();
		
		//Esta Capturando a sessao
		ControleLogin controlerLogin =(ControleLogin) sessao.getAttribute("controleLogin");
		
		if(controlerLogin == null || controlerLogin.getUsuarioLogado() == null) {
			
			httpResponser.sendRedirect(contextPath+"/login.xhtml");
			
		}else {
			
			String pagina = httpRequest.getRequestURI().toString();
			System.out.println("Pagina Acessada:"+pagina);
			
			if(pagina.contains("/privado/funcionario")) {
				
				System.out.println("Usuario :"+controlerLogin.getUsuarioLogado()+" Grupo :"+controlerLogin.getUsuarioLogado().getGrupo());
				
				if(!controlerLogin.getUsuarioLogado().getGrupo().getNome().equals("Administrativo")){
				
					
					httpResponser.sendRedirect(contextPath+"/naoAutorizado.xhtml");
					
					
				}
						
			}
			
			
			
		}
		chain.doFilter(request, httpResponser);
		
	}
	
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}



}
