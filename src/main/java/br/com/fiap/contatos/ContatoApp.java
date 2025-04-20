package br.com.fiap.contatos;

import br.com.fiap.contatos.dao.Conexao;
import br.com.fiap.contatos.dao.ContatoDao;
import br.com.fiap.contatos.dao.TipoContatoDao;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.model.TipoContato;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class ContatoApp {

    public static void main(String[] args) {

        EntityManager em = Conexao.getEntityManager();
        cadastrar(em);
//        atualizar(em);
//        excluir(em);
//        consultarContatoPorId(em);
//        listarTodosOsContatos(em);
//        listaContatosPorEmail(em);
        consultarTipoContatoPeloId(em);
    }

    private static void consultarTipoContatoPeloId(EntityManager em) {
        TipoContatoDao tipoContatoDao = new TipoContatoDao(em);
        TipoContato tipoContatoBuscado = new TipoContato();
        tipoContatoBuscado.setId(2L);

        TipoContato tipoContatoEncontrado = new TipoContato();
        tipoContatoEncontrado = tipoContatoDao.buscarTipoContatoPeloId(tipoContatoBuscado);

        System.out.println(tipoContatoEncontrado.getTipo());
        System.out.println(tipoContatoEncontrado.getContatos());
    }

    private static void listaContatosPorEmail(EntityManager em) {
        ContatoDao contatoDao = new ContatoDao(em);

        List<Contato> contatos = contatoDao.listaContatosPorEmail("teste2@example.com");

        for (Contato contato : contatos) {
            System.out.println("______________________________________");
            System.out.println(contato);
        }

        System.out.println("Sem mais registros");
    }

    public static void listarTodosOsContatos(EntityManager em) {

        ContatoDao contatoDao = new ContatoDao(em);

        List<Contato> contatos = contatoDao.listarTodosOsContatos();

        for (Contato contato : contatos) {
            System.out.println("______________________________________");
            System.out.println(contato);
        }
        System.out.println("Sem mais registros");
    }


    public static void cadastrar(EntityManager em) {

        TipoContato tipoContato = new TipoContato();
        tipoContato.setId(2L);
        tipoContato.setTipo("Familia");

        TipoContatoDao tipoContatoDao = new TipoContatoDao(em);

        em.getTransaction().begin();
//        tipoContatoDao.salvar(tipoContato);

        Contato contato = new Contato();
        contato.setNome("Isabelle Cunha");
        contato.setEmail("isabelle@example.com");
        contato.setDataNascimento(LocalDate.of(2019, 10, 4));
        contato.setTipoContato(tipoContato);

        //Criação da instância do Dao
        ContatoDao contatoDao = new ContatoDao(em);

        contatoDao.salvar(contato);
        em.getTransaction().commit();
    }

    public static void atualizar(EntityManager em) {
        Contato contato = new Contato();
        contato.setId(7L);
        contato.setNome("Teste5");
        contato.setEmail("teste5@example.com");
        contato.setDataNascimento(LocalDate.of(1989, 10, 27));

        //Criação da instância do Dao
        ContatoDao dao = new ContatoDao(em);

        em.getTransaction().begin();
        dao.atualizar(contato);
        em.getTransaction().commit();
    }

    private static void excluir(EntityManager em) {
        Contato contato = new Contato();
        contato.setId(1L);

        //Criação da instância do Dao
        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.excluir(contato);
        em.getTransaction().commit();
    }

    private static void consultarContatoPorId(EntityManager em) {
        //Criação da instância do Dao
        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.ConsultarContatoPorId(11L);
        em.getTransaction().commit();
    }
}

