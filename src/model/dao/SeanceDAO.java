package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.entity.Seance;
import model.entity.EnumModule;
import model.entity.EnumPresence;
import model.entity.Etudiant;
import model.entity.Promotion;

public class SeanceDAO extends DAO<Seance, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
public int create(Seance seance) {
    String query = "INSERT INTO Seance (etudiant, module, statut, dateSeance, horaireDebut, horaireFin, dateSaisie) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    int lignesInsereres = 0;

    try (Connection con = getConnection();
         PreparedStatement st = con.prepareStatement(query)) {
        
        // On boucle sur chaque étudiant présent dans la HashMap 'appel'
        for (Map.Entry<Etudiant, EnumPresence> entree : seance.getAppel().entrySet()) {
            Etudiant etudiant = entree.getKey();
            EnumPresence statut = entree.getValue();

            st.setInt(1, etudiant.getNum()); 
            st.setString(2, seance.getModule().getIntitule()); 
            st.setString(3, statut.name()); 
            
            // 4. La date de la séance (java.sql.Date)
            st.setDate(4, new java.sql.Date(seance.getDate().getTime())); 
            
            // 5. Horaire début (Conversion LocalTime -> java.sql.Time)
            st.setTime(5, java.sql.Time.valueOf(seance.getHeureDeb())); 
            
            // 6. Horaire fin (Conversion LocalTime -> java.sql.Time)
            st.setTime(6, java.sql.Time.valueOf(seance.getHeureFin())); 
            
            // 7. Date de saisie (On met la date du jour actuel)
            st.setDate(7, new java.sql.Date(System.currentTimeMillis()));

            lignesInsereres += st.executeUpdate();
        }
        
        return lignesInsereres; // Renvoie le nombre total d'étudiants enregistrés

    } catch (SQLException ex) {
        ex.printStackTrace();
        return -1;
    }
}

    @Override
    public List<Module> findAll() {
        List<Module> modules = new LinkedList<>();
        try (Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Module")) {
            while (rs.next()) {
                String code = rs.getString("code");
                String intitule = rs.getString("intitule");
                String type = rs.getString("typeModule");
                String idPromo = rs.getString("promo");
                PromotionDAO promotionDAO = new PromotionDAO();
                Promotion promo = promotionDAO.findByID(idPromo);

                if (promo != null) {
                    modules.add(new Module(code, intitule, EnumModule.valueOf(type), promo));
                }
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return modules;
    }


    @Override
    public Module findByID(String code) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT * FROM Module WHERE intitule= ?")) {
            st.setString(1, code);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String codeMod = rs.getString("intitule");
                    String intitule = rs.getString("nom");
                    String typeMod = rs.getString("typeModule");
                    String idPromo = rs.getString("idPromo");
                    PromotionDAO promotionDAO = new PromotionDAO();
                    Promotion promo = promotionDAO.findByID(idPromo);

                    return new Module(codeMod, intitule,  EnumModule.valueOf(typeMod), promo);
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
    public int update(Module module) {
        String query = "UPDATE Module SET intitule= ?, nom= ?, typeModule=?, promo=?,  WHERE intitule= ? ";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, module.getCode());
            st.setString(2, module.getIntitule());
            st.setString(3, module.getPromo().getNomPromo().name());
            st.setString(4, module.getType().name());
            st.setString(5, module.getIntitule());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(Module module) {
        String query = "DELETE FROM Module WHERE intitule= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, module.getIntitule());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
