package br.com.edsonvieira.salaodeunhas.persistencia;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.edsonvieira.salaodeunhas.model.Servico;

@Dao
public interface ServicoDao {

    @Insert
    long insert(Servico servico);

    @Update
    int update(Servico servico);

    @Delete
    int delete(Servico servico);

    @Query("SELECT * FROM servico ORDER BY descricao ASC")
    List<Servico> queryAllAscending();

    @Query("SELECT * FROM servico WHERE id = :id")
    Servico queryForId(long id);

    @Query("SELECT COUNT(*) FROM servico")
    int countServicos();

}
