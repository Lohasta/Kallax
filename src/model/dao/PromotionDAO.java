package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.entity.EnumPromo;
import model.entity.Promotion;

public class PromotionDAO extends DAO<Promotion, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
    public int create(Promotion promotion) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("INSERT INTO Promotion VALUES (?)")) {
            st.setString(1, promotion.getNomPromo().toString());
            try {
                return st.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Promotion> findAll() {
        List<Promotion> promotions = new LinkedList<>();
        try (Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Promotion")) {
            while (rs.next()) {
                System.out.println("AZAZAZAZAZA");
                String code = rs.getString("code");
                promotions.add(Promotion.getPromotionByName(code));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promotions;
    }

    @Override
    public Promotion findByID(String code) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT * FROM Promotion WHERE code= ?")) {
            st.setString(1, code);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String codeRs = rs.getString("code");
                    return Promotion.getPromotionByName(code);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(Promotion promotion) {

        // Nothing to change for a promotion ou pas je sais pas en fait à quoi ça servirai de changer ça sachant qu'il y en aura que 3 qui bougent pas
        return 0;



        /*String query = "UPDATE Utilisateur SET motDePasse= ?, nom= ?, prenom= ? WHERE email= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, utilisateur.getMdp());
            st.setString(2, utilisateur.getNom());
            st.setString(3, utilisateur.getPrenom());
            st.setString(4, utilisateur.getEmail());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }*/
    }

    @Override
    public int delete(Promotion promotion) {
        String query = "DELETE FROM Promotion WHERE code= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, promotion.getNomPromo().toString());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
