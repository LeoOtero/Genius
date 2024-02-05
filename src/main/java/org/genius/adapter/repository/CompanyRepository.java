package org.genius.adapter.repository;

import org.genius.entity.table.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAll();

    Company findById(Integer id);

}
