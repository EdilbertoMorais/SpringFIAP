package br.com.fiap.contatos.dao;

import br.com.fiap.contatos.model.TipoContato;
import jakarta.persistence.EntityManager;

public class TipoContatoDao {

    private EntityManager em;

    public TipoContatoDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(TipoContato tipoContato) {
        em.persist(tipoContato);
    }

    public TipoContato buscarTipoContatoPeloId(TipoContato tipoContato) {
        return em.find(TipoContato.class, tipoContato.getId());
    }
}
