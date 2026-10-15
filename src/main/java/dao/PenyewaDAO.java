package dao;

import model.Penyewa;

import java.sql.SQLException;
import java.util.List;

public interface PenyewaDAO {

    List<Penyewa> getAll() throws SQLException;

    void insert(Penyewa penyewa) throws SQLException;

    void update(Penyewa penyewa) throws SQLException;

    void delete(int id) throws SQLException;
}