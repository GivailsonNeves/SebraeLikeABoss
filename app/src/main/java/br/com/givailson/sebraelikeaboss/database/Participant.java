package br.com.givailson.sebraelikeaboss.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity
public class Participant {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "nome")
    public String name;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "nome_empresa")
    public String enterpriseName;

    @ColumnInfo(name = "setor_atuacao")
    public String actuationSector;

    @ColumnInfo(name = "data_participacao")
    public Date participationDate;

    @Ignore
    public Participant(String name, String email, String enterpriseName, String actuationSector, Date participationDate) {
        this.name = name;
        this.email = email;
        this.enterpriseName = enterpriseName;
        this.actuationSector = actuationSector;
        this.participationDate = participationDate;
    }

    public Participant(int uid, String name, String email, String enterpriseName, String actuationSector, Date participationDate) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.enterpriseName = enterpriseName;
        this.actuationSector = actuationSector;
        this.participationDate = participationDate;
    }
}
