package com.thec.card.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thec.card.domain.Card;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {

}
