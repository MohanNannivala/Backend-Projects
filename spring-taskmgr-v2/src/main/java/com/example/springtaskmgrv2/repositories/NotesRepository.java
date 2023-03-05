package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {
}
