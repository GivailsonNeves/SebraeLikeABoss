package br.com.givailson.sebraelikeaboss.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ParticipantDao {

    @Query("SELECT * FROM participant")
    LiveData<List<Participant>> getAllParticipants();

    @Query("SELECT * FROM participant")
    List<Participant> getAllParticipantsList();

    @Insert
    void insert(Participant participant);

    @Query("SELECT * FROM participant WHERE email like :email")
    Participant getByEmail(String email);

    @Delete
    void delete(Participant participant);
}
