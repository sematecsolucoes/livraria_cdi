package br.com.sematec.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sematec.livraria.dao.DAO;
import br.com.sematec.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {
	private Autor autor = new Autor();
	private Integer autorId;

	public void carregarAutorPelaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		this.autor = new Autor();
		return "livro?faces-redirect=true";
	}

	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		new DAO<Autor>(Autor.class).remove(autor);
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
}
