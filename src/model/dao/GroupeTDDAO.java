package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.entity.Etudiant;
import model.entity.GroupeTD;
import model.entity.Module;
import model.entity.Promotion;

public class GroupeTDDAO extends DAO<GroupeTD, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
    public int create(GroupeTD groupeTD) {
        try (Connection con = getConnection()){

            try(PreparedStatement st = con.prepareStatement("INSERT INTO GroupeTD (sigle, promo) VALUES (?, ?)")){
                st.setString(1, groupeTD.getNom());
                st.setString(2, groupeTD.getPromotion().getNomPromo().name());
                st.executeUpdate();
            }
        
            if (groupeTD.getModulesSuivis() != null) {
                try (PreparedStatement stS = con.prepareStatement("INSERT INTO Suivre (sigleGroupe, promoGroupe, idModule) VALUES (?, ?, ?)")) {
                    for (Module m : groupeTD.getModulesSuivis()) {
                        stS.setString(1, groupeTD.getNom());
                        stS.setString(2, groupeTD.getPromotion().getNomPromo().name());
                        stS.setString(3, m.getIntitule());
                        stS.executeUpdate();
                    }
                }
            }

            return 1;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<GroupeTD> findAll() {
        List<GroupeTD> groupes = new LinkedList<>();
        PromotionDAO promotionDAO = new PromotionDAO();

        try (Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM GroupeTD")) {
            while (rs.next()) {
                String nom = rs.getString("sigle");
                String idPromo = rs.getString("promo");
                
                Promotion promo = promotionDAO.findByID(idPromo);
                
                if ( promo != null){
                    GroupeTD groupe = new GroupeTD(nom, promo);

                    groupe.setEtudiants(EtudiantsInGroupe(con, nom, idPromo));
                    groupe.setModulesSuivis(ModulesInGroupe(con, nom, idPromo));

                    ArrayList<GroupeTD> groupesPromo = promo.getGroupes();
                    boolean found = false;
                    GroupeTD oldGrp = null;
                    for (GroupeTD grp : groupesPromo) {
                        if(nom.equals(grp.getNom())) {
                            found = true;
                            oldGrp = grp;
                        }
                    }
                    if (found) {
                        groupesPromo.remove(oldGrp);
                        groupesPromo.add(groupe);
                    }
                    groupes.add(groupe);
                }
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groupes;
    }

    public GroupeTD findByKey(String sigle, String promo){
        String key = sigle + "_" + promo;
        return findByID(key);
    }

    @Override
    public GroupeTD findByID(String key) {

        String[] morceaux = key.split("_");
        String sigleKey = morceaux[0];
        String promoKey = morceaux[1];

        PromotionDAO promotionDAO = new PromotionDAO();

        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT * FROM GroupeTD WHERE sigle= ? AND promo= ?")) {
            st.setString(1, sigleKey);
            st.setString(2, promoKey);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("sigle");
                    String idPromo = rs.getString("promo");
                    Promotion promo = promotionDAO.findByID(idPromo);

                    if (promo != null) {
                        GroupeTD groupe = new GroupeTD(nom, promo);
                        groupe.setEtudiants(EtudiantsInGroupe(con, nom, idPromo));
                        groupe.setModulesSuivis(ModulesInGroupe(con, nom, idPromo));
                        
                        ArrayList<GroupeTD> groupesPromo = promo.getGroupes();
                        boolean found = false;
                        GroupeTD oldGrp = null;
                        for (GroupeTD grp : groupesPromo) {
                            if(nom.equals(grp.getNom())) {
                                found = true;
                                oldGrp = grp;
                            }
                        }
                        if (found) {
                            groupesPromo.remove(oldGrp);
                            groupesPromo.add(groupe);
                        }

                        return groupe;
                    }
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
        String query = "UPDATE GroupeTD SET sigle= ?, promo= ? WHERE sigle= ? AND promo= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, groupe.getNom());
            st.setString(2, groupe.getPromotion().getNomPromo().name());
            st.setString(3, groupe.getNom());
            st.setString(4, groupe.getPromotion().getNomPromo().name());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(GroupeTD groupe) {
        String query = "DELETE FROM GroupeTD WHERE sigle= ? AND promo= ?";
        
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, groupe.getNom());
            st.setString(2, groupe.getPromotion().getNomPromo().name());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }



    private ArrayList<Etudiant> EtudiantsInGroupe(Connection con, String sigle, String promo) throws SQLException {
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        EtudiantDAO etudiantDAO = new EtudiantDAO();

        try (PreparedStatement st = con.prepareStatement("SELECT numero FROM Etudiant WHERE groupeTD = ? AND promo = ?")) {
            st.setString(1, sigle);
            st.setString(2, promo);
            
            try (ResultSet rs = st.executeQuery()) {
                
                while (rs.next()) {
                    int etuID = rs.getInt("numero");
                    Etudiant etu = etudiantDAO.findByID(etuID);
                    
                    if (etu != null) {
                        etudiants.add(etu);
                    }
                }
            }
        }
        return etudiants;
    }  

    private ArrayList<Module> ModulesInGroupe(Connection con, String sigle, String promo) throws SQLException {
        ArrayList<Module> modules = new ArrayList<>();
        ModuleDAO moduleDAO = new ModuleDAO();

        try (PreparedStatement st = con.prepareStatement("SELECT idModule FROM Suivre WHERE sigleGroupe = ? AND promoGroupe = ?")) {
            st.setString(1, sigle);
            st.setString(2, promo);
            
            try (ResultSet rs = st.executeQuery()) {
                
                while (rs.next()) {
                    String moduleID = rs.getString("idModule");
                    Module m = moduleDAO.findByID(moduleID);
                    if (m != null){
                        modules.add(m);
                    } 
                }
            }
        }
        return modules;
    }
}
