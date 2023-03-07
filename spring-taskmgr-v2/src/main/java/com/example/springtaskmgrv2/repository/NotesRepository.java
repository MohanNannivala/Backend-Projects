package com.example.springtaskmgrv2.repository;

import com.example.springtaskmgrv2.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {
}
