package br.com.thec.cartao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.thec.cartao.domain.Cartao;

@Repository
public interface CartaoRepository extends MongoRepository<Cartao, String> {

}
