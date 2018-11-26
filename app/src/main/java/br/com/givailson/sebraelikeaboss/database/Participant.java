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

    @ColumnInfo(name = "tem_negocio")
    public String hasBusiness;

    @ColumnInfo(name = "tempo_negocio")
    public String timeOfBusiness;

    @ColumnInfo(name = "setor_atuacao")
    public String actuationSector;

    @ColumnInfo(name = "vende_pela_internet")
    public boolean sellForInternet;

    @ColumnInfo(name = "diferencial_negocio")
    public String businessPlus;

    @ColumnInfo(name = "data_participacao")
    public Date participationDate;

    @Ignore
    public Participant() {}

    @Ignore
    public Participant(String name, String email, String enterpriseName, String hasBusiness, String timeOfBusiness, String actuationSector, boolean sellForInternet, String businessPlus, Date participationDate) {
        this.name = name;
        this.email = email;
        this.enterpriseName = enterpriseName;
        this.hasBusiness = hasBusiness;
        this.timeOfBusiness = timeOfBusiness;
        this.actuationSector = actuationSector;
        this.sellForInternet = sellForInternet;
        this.businessPlus = businessPlus;
        this.participationDate = participationDate;
    }

    public Participant(int uid, String name, String email, String enterpriseName, String hasBusiness, String timeOfBusiness, String actuationSector, boolean sellForInternet, String businessPlus, Date participationDate) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.enterpriseName = enterpriseName;
        this.hasBusiness = hasBusiness;
        this.timeOfBusiness = timeOfBusiness;
        this.actuationSector = actuationSector;
        this.sellForInternet = sellForInternet;
        this.businessPlus = businessPlus;
        this.participationDate = participationDate;
    }
}
