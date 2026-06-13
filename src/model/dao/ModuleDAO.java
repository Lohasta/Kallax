package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.entity.EnumModule;
import model.entity.Module;
import model.entity.Promotion;

public class ModuleDAO extends DAO<Module, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
    public int create(Module module) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("INSERT INTO Module (intitule, nom, typeModule, promo) VALUES (?, ?, ?, ?)")) {
            st.setString(1, module.getCode());
            st.setString(2, module.getIntitule());
            st.setString(3, module.getType().name());
            st.setString(4, module.getPromo().getNomPromo().name());
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
