package com.bryan;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<T, ID> where T is type of entity, which is SWE, and ID is the type of primary key
public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Integer> {

}
