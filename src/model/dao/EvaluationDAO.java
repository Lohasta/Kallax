package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.entity.Etudiant;
import model.entity.Evaluation;
import model.entity.Module;


public class EvaluationDAO extends DAO<Evaluation, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
    public int create(Evaluation eval) {
        try (Connection con = getConnection();
            PreparedStatement stNote = con.prepareStatement("INSERT INTO Note (etudiant, eval, valeur, statut) VALUES (?, ?, ?, ?) ");
            PreparedStatement stEval = con.prepareStatement("INSERT INTO Evaluation (id, coeff, module) VALUES (?, ?, ?)")) {
                stEval.setString(1, eval.getIdent());
                stEval.setDouble(2, eval.getCoeff());
                stEval.setString(3, eval.getModule().getIntitule());
                
                int result = stEval.executeUpdate();

                if (result > 0 && eval.getNotes() != null) {

                    // boucle sur la HashMap pour insérer chaque note
                    for (Map.Entry<Etudiant, Double> donnee : eval.getNotes().entrySet()){
                        Etudiant etudiant = donnee.getKey();
                        Double note = donnee.getValue();

                        stNote.setInt(1, etudiant.getNum());
                        stNote.setString(2, eval.getIdent());
                        stNote.setDouble(3, note);
                        stNote.setString(4, "OK");

                        stNote.executeUpdate();
                    }
                }

            return result;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
     

    @Override
    public List<Evaluation> findAll() {
        List<Evaluation> evaluations = new LinkedList<>();

        ModuleDAO moduleDAO = new ModuleDAO();
        EtudiantDAO etudiantDAO = new EtudiantDAO();

        try (Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rsEval = st.executeQuery("SELECT * FROM Evaluation"))
                {
            
            while (rsEval.next()) {
                String id = rsEval.getString("id");
                Double coeff = rsEval.getDouble("coeff");

                String idModule = rsEval.getString("module");
                Module module = moduleDAO.findByID(idModule);

                HashMap<Etudiant, Double> notes = new HashMap<>();

                try(
                    PreparedStatement stNote = con.prepareStatement("SELECT etudiant, valeur FROM Note WHERE eval=?")){
                    stNote.setString(1, id);

                    try(
                        ResultSet rsNote = stNote.executeQuery()){

                        while(rsNote.next()){
                            int numEtu = rsNote.getInt("etudiant");
                            double note = rsNote.getDouble("valeur");
                        
                            Etudiant etu = etudiantDAO.findByID(numEtu);
                            notes.put(etu, note);  
                        }
                    }
                }

                Evaluation eval = new Evaluation(id, coeff, module);
                eval.setNotes(notes);
                evaluations.add(eval);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return evaluations;
    }


    @Override
    public Evaluation findByID(String id) {

        EtudiantDAO etudiantDAO = new EtudiantDAO();
        
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT * FROM Evaluation WHERE id= ?")) {
            st.setString(1, id);
             
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String idEval = rs.getString("id");
                    Double coeff = rs.getDouble("coeff");
                    String moduleId = rs.getString("module");

                    ModuleDAO moduleDAO = new ModuleDAO();
                    Module module = moduleDAO.findByID(moduleId);

                    HashMap<Etudiant, Double> notes = new HashMap<>();

                    try(
                        PreparedStatement stNote = con.prepareStatement("SELECT etudiant, valeur FROM Note WHERE eval=?")){
                        stNote.setString(1, id);

                        try(
                            ResultSet rsNote = stNote.executeQuery()){

                            while(rsNote.next()){
                                int numEtu = rsNote.getInt("etudiant");
                                double note = rsNote.getDouble("valeur");
                                
                                
                                Etudiant etu = etudiantDAO.findByID(numEtu);
                                notes.put(etu, note);  
                            }
                        }
                    }

                    Evaluation eval = new Evaluation(idEval, coeff, module);
                    eval.setNotes(notes);

                    return eval;
                       
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
    public int update(Evaluation eval) {
        String query = "UPDATE Evaluation SET id= ?, coeff= ?, module=? WHERE id= ? ";
        String querySuppr = "DELETE FROM Note WHERE eval= ?";
        String queryNote = "INSERT INTO Note (etudiant, eval, valeur, statut) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, eval.getIdent());
            st.setDouble(2, eval.getCoeff());
            st.setString(3, eval.getModule().getIntitule());
            st.setString(4, eval.getIdent());
            
            int result = st.executeUpdate();

            //on delete les notes pour tout reinserer apres
            try(PreparedStatement stSuppr = con.prepareStatement(querySuppr)){
                stSuppr.setString(1,eval.getIdent());
                stSuppr.executeUpdate();
            }

            try (PreparedStatement stNote = con.prepareStatement(queryNote)){
                for (Map.Entry<Etudiant, Double> donnee : eval.getNotes().entrySet()){
                    Etudiant etudiant = donnee.getKey();
                    Double note = donnee.getValue();

                    stNote.setInt(1, etudiant.getNum());
                    stNote.setString(2, eval.getIdent());
                    stNote.setDouble(3, note);
                    stNote.setString(4, "OK");
                    stNote.executeUpdate();
                }
            }

            return result;
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }


    @Override
    public int delete(Evaluation eval) {
        String query = "DELETE FROM Evaluation WHERE id= ?";
        String querySuppr = "DELETE FROM Note WHERE eval= ?";
        
        try(Connection con = getConnection()){
            try(PreparedStatement stSuppr = con.prepareStatement(querySuppr)){
                stSuppr.setString(1,eval.getIdent());
                stSuppr.executeUpdate();
            }
            try (
                    PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, eval.getIdent());
                return st.executeUpdate();

            }
        }
         catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
