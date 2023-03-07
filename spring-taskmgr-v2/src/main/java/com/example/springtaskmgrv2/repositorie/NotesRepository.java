package com.example.springtaskmgrv2.repositorie;

import com.example.springtaskmgrv2.entitie.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {
}
