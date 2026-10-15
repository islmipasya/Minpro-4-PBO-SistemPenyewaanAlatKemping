package dao;

import model.Sewa;

import java.sql.SQLException;
import java.util.List;

public interface SewaDAO {

    List<Sewa> getAll() throws SQLException;

    void insert(Sewa sewa) throws SQLException;

    void update(Sewa sewa) throws SQLException;

    void delete(int id) throws SQLException;
}