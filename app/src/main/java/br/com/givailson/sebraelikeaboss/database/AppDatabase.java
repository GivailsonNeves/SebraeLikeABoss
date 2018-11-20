package br.com.givailson.sebraelikeaboss.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import br.com.givailson.sebraelikeaboss.utils.DateTypeConverter;

@Database(entities = {Participant.class}, version = 1, exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ParticipantDao participantDao();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
           synchronized (AppDatabase.class) {
               if (INSTANCE == null) {
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                           AppDatabase.class, "sebrae_like_boss_db")
                           .build();
               }
           }
        }
        return INSTANCE;
    }
}
