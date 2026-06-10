package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.entity.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }
}
