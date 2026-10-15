package dao;

import model.AlatKemping;

import java.sql.SQLException;
import java.util.List;

public interface AlatDAO {

    List<AlatKemping> getAll() throws SQLException;

    void insert(AlatKemping alat) throws SQLException;

    void update(AlatKemping alat) throws SQLException;

    void delete(int id) throws SQLException;
}