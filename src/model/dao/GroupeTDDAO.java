package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.entity.GroupeTD;
import model.entity.Promotion;

public class GroupeTDDAO extends DAO<GroupeTD, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
    public int create(GroupeTD groupeTD) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("INSERT INTO GroupeTD (sigle, promo) VALUES (?, ?)")) {
            st.setString(1, groupeTD.getNom());
            st.setString(2, groupeTD.getPromotion().getNomPromo().toString());
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
    public List<GroupeTD> findAll() {
        List<GroupeTD> groupes = new LinkedList<>();
        try (Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM GroupeTD")) {
            while (rs.next()) {
                String nom = rs.getString("sigle");
                String idPromo = rs.getString("promo");
                PromotionDAO promotionDAO = new PromotionDAO();
                Promotion promo = promotionDAO.findByID(idPromo);
                
                if ( promo != null){
                    groupes.add(new GroupeTD(nom, promo));
                }
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groupes;
    }


    @Override
    public GroupeTD findByID(String nom) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT * FROM GroupeTD WHERE sigle= ?")) {
            st.setString(1, nom);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String grNom = rs.getString("sigle");
                    String idPromo = rs.getString("idPromo");
                    PromotionDAO promotionDAO = new PromotionDAO();
                    Promotion promo = promotionDAO.findByID(idPromo);

                    return new GroupeTD(grNom, promo);
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
    public int update(GroupeTD groupe) {
        String query = "UPDATE GroupeTD SET sigle= ?, promo= ?, WHERE sigle= ? ";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, groupe.getNom());
            
            st.setString(2, groupe.getPromotion().getNomPromo().name());
            st.setString(3, groupe.getNom());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(GroupeTD groupe) {
        String query = "DELETE FROM GroupeTD WHERE sigle= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, groupe.getNom());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
