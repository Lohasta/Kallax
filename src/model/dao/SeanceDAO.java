package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.*;
import model.entity.EnumPresence;
import model.entity.Etudiant;
import model.entity.GroupeTD;
import model.entity.Module;
import model.entity.Seance;

public class SeanceDAO extends DAO<Seance, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
    public int create(Seance seance) {
    String query = "INSERT INTO Seance (etudiant, module, statut, dateSeance, horaireDebut, horaireFin, groupeTD) VALUES (?, ?, ?, ?, ?, ?, ?)"; 
    int result = -1;

    try (Connection con = getConnection();
         PreparedStatement st = con.prepareStatement(query)) {
        
        // On boucle sur chaque étudiant présent dans la HashMap 'appel'
        for (Map.Entry<Etudiant, EnumPresence> entree : seance.getAppel().entrySet()) {
            Etudiant etudiant = entree.getKey();
            EnumPresence statut = entree.getValue();

            st.setInt(1, etudiant.getNum()); 
            st.setString(2, seance.getModule().getIntitule()); 
            st.setString(3, statut.name()); 
            st.setDate(4, new java.sql.Date(seance.getDate().getTime())); 
            st.setTime(5, java.sql.Time.valueOf(seance.getHeureDeb())); 
            st.setTime(6, java.sql.Time.valueOf(seance.getHeureFin())); 
            st.setDate(7, new java.sql.Date(System.currentTimeMillis()));
            result = st.executeUpdate();
        }
        
        return result;

    } catch (SQLException ex) {
        ex.printStackTrace();
        return -1;
    }
}

    @Override
    public List<Seance> findAll() {

        Map<String, Seance> seancesDict = new LinkedHashMap<>();
        ModuleDAO moduleDAO = new ModuleDAO();
        GroupeTDDAO groupeDAO = new GroupeTDDAO(); 
        EtudiantDAO etudiantDAO = new EtudiantDAO();

        try (Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Seance ORDER BY dateSeance, horaireDebut, module")) {
            
            while (rs.next()) {
            
                String idModule = rs.getString("module");
                java.sql.Date dateSeanceSql = rs.getDate("dateSeance");
                Date dateSeance = new Date(dateSeanceSql.getTime());

                java.sql.Time heureDebSql = rs.getTime("horaireDebut");
                LocalTime heureDeb = heureDebSql.toLocalTime();
                
                //on cree une cle pour verifier si la seance a deja ete vue, ça permet d'ordonner
                String cleSeance = idModule + "_" + dateSeance.toString() + "_" + heureDeb.toString();
                if (!seancesDict.containsKey(cleSeance)) {
                    Module module = moduleDAO.findByID(idModule);
                    LocalTime heureFin = rs.getTime("horaireFin").toLocalTime();
                
                    String idGroupe = rs.getString("groupeTD");
                    GroupeTD groupe = groupeDAO.findByID(idGroupe);

                    if (module != null && groupe != null) {
                        Seance nouvelleSeance = new Seance(dateSeance, heureDeb, heureFin, module, groupe);
                        seancesDict.put(cleSeance, nouvelleSeance);
                    }
                }

                Seance seance = seancesDict.get(cleSeance);
                if (seance != null) {
                    
                    int numEtu = rs.getInt("etudiant");
                    Etudiant etu = etudiantDAO.findByID(numEtu);
                    String statutEtu = rs.getString("statut");
                    
                    if (etu != null && statutEtu != null) {
                        EnumPresence statut = EnumPresence.valueOf(statutEtu);
                        seance.getAppel().put(etu, statut);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        List<Seance> seances = new LinkedList<>(seancesDict.values());
        return seances;
    }


    /**
     * Finds a seance by its module ID, date, and start time.
     * @param idModule module of the seance
     * @param dateSeance date of the seance
     * @param heureDeb start time of the seance
     * @return the seance found, else null
     */
    public Seance findByKey(String idModule, Date dateSeance, LocalTime heureDeb){
        String key = idModule + "_" + dateSeance.toString() + "_" + heureDeb.toString();
        return findByID(key);
    }


    @Override
    public Seance findByID(String key) {

        ModuleDAO moduleDAO = new ModuleDAO();
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        GroupeTDDAO groupeDAO = new GroupeTDDAO();
        Seance seance = null;

        try (Connection con = getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM Seance WHERE module=?, dateSeance=?, horaireDebut=?")) {
            
            String[] parts = key.split("_");
            st.setString(1, parts[0]);
            st.setDate(2, java.sql.Date.valueOf(parts[1]));
            st.setTime(3, java.sql.Time.valueOf(parts[2]));
            
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {

                    // on cree la Séance une seule fois
                    if (seance == null){

                        String moduleID = rs.getString("module");
                        Module module = moduleDAO.findByID(moduleID);

                        java.sql.Date dateSeanceSql = rs.getDate("dateSeance");
                        Date dateSeance = new Date(dateSeanceSql.getTime());

                        LocalTime heureDeb = rs.getTime("horaireDebut").toLocalTime();
                        LocalTime heureFin = rs.getTime("horaireFin").toLocalTime();

                        String groupeID = rs.getString("groupeTD");
                        GroupeTD groupeTD = groupeDAO.findByID(groupeID);

                        if (module != null && groupeTD != null){
                            seance = new Seance(dateSeance, heureDeb, heureFin, module, groupeTD);
                        }
                    }

                    //si la séance existe, on peut ajouter la présence des étudiants
                    if (seance != null){
                        int etuID = rs.getInt("etudiant");
                        Etudiant etu = etudiantDAO.findByID(etuID);
                        String statutID = rs.getString("statut");

                        if (etu != null && statutID != null) {
                            EnumPresence statut = EnumPresence.valueOf(statutID);
                            seance.getAppel().put(etu, statut);
                        }

                    }
                    
                }    
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return seance;
    }



    @Override
    public int update(Seance seance) {
        String query = "UPDATE Seance SET statut = ?, horaireFin = ?, groupeTD = ? WHERE etudiant = ? AND module = ? AND dateSeance = ? AND horaireDebut = ?";
        
        int result = 0;
        try (Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(query)) {
            
            for (Map.Entry<Etudiant, EnumPresence> appel : seance.getAppel().entrySet()) {
                Etudiant etudiant = appel.getKey();
                EnumPresence statut = appel.getValue();
                st.setString(1, statut.name());
                st.setTime(2, java.sql.Time.valueOf(seance.getHeureFin()));
                st.setString(3, seance.getGroupe().getNom()); 
                st.setInt(4, etudiant.getNum());
                st.setString(5, seance.getModule().getIntitule());
                st.setDate(6, new java.sql.Date(seance.getDate().getTime()));
                st.setTime(7, java.sql.Time.valueOf(seance.getHeureDeb()));

                result += st.executeUpdate();
            }  

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }


    @Override
    public int delete(Seance seance) {
        String query = "DELETE FROM Seance WHERE etudiant = ? AND module = ? AND dateSeance = ? AND horaireDebut = ?";
        try (Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(query)) {
            
            st.setString(1, seance.getModule().getIntitule());
            st.setDate(2, new java.sql.Date(seance.getDate().getTime()));
            st.setTime(3, java.sql.Time.valueOf(seance.getHeureDeb()));


            return st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
