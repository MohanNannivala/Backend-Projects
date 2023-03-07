package com.example.springtaskmgrv2.repository;

import com.example.springtaskmgrv2.entity.NoteEntity;
import com.example.springtaskmgrv2.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {

    public List<NoteEntity> findAllByTask(TaskEntity task);

    @Modifying
    @Transactional
    @Query("delete from notes n where n.task=:task and n.id=:noteId")
    public void deleteByTaskIdAndNoteId(TaskEntity task, Integer noteId);
}
