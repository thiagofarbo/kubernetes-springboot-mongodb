package br.com.thec.cartao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.thec.cartao.domain.Cartao;

public interface CartaoRepository extends MongoRepository<Cartao, String> {

}
