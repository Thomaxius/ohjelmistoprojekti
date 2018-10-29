package com.ohjelmistoprojekti.domain;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface Categoryrepo extends CrudRepository<Category, Long> {

	List<Category> findByCategory(String categoryid);
}
