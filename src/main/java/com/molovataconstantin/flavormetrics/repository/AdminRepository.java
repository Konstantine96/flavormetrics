package com.molovataconstantin.flavormetrics.repository;

import com.molovataconstantin.flavormetrics.entity.Impl.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

}