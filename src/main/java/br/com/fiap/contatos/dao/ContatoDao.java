package br.com.fiap.contatos.dao;

import br.com.fiap.contatos.model.Contato;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ContatoDao {

    private EntityManager em;

    public ContatoDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Contato contato) {
        em.persist(contato);
    }

    public void atualizar(Contato contato) {
        em.merge(contato);
    }

    public void excluir(Contato contato) {
        Contato contatoExcluir = em.find(Contato.class, contato.getId());
        em.remove(contatoExcluir);

    }

    public void ConsultarContatoPorId(Long id) {
        Contato contatoConsulta = em.find(Contato.class, id);

        if (contatoConsulta == null) {
            System.out.println("Contato não encontrado!");
        }else {
            System.out.println("___________________________________");
            System.out.println(contatoConsulta.toString());
            System.out.println("___________________________________");
        }
    }

    public List<Contato> listarTodosOsContatos() {

        String consulta = "SELECT c FROM Contato c ORDER BY c.nome ASC";

        return em.createQuery(consulta).getResultList();
    }

    public List<Contato> listaContatosPorEmail(String email) {
        String consulta  = "SELECT c FROM Contato c WHERE c.email = :email";

        return em.createQuery(consulta)
                .setParameter("email", email)
                .getResultList();
    }

}
