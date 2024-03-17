package br.com.edsonvieira.salaodeunhas.persistencia;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.edsonvieira.salaodeunhas.model.Servico;

@Database(entities = {Servico.class}, version = 1)
public abstract class SalaoDeUnhasDatabase extends RoomDatabase {

    private static SalaoDeUnhasDatabase instance;

    public static SalaoDeUnhasDatabase getDatabase(final Context context) {

        if (instance == null) {
            synchronized (SalaoDeUnhasDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            SalaoDeUnhasDatabase.class,
                            "SalaoDeUnhas.db").allowMainThreadQueries().build();
                    return instance;
                }
            }
        }
        return instance;
    }

    public abstract ServicoDao getServicoDao();




}
