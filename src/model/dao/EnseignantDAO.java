package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.entity.DirecteurEtudes;
import model.entity.Enseignant;

public class EnseignantDAO extends DAO<Enseignant, String> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
    public int create(Enseignant enseignant) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("INSERT INTO Enseignant VALUES (?, ?, ?)")) {
            st.setString(1, enseignant.getIdent());
            st.setBoolean(2, enseignant instanceof DirecteurEtudes);
            st.setString(3, enseignant.getEmail());
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
    public List<Enseignant> findAll() {
        List<Enseignant> enseignants = new LinkedList<>();
        try (Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Enseignant")) {
            while (rs.next()) {
                String identifiant = rs.getString("identifiant");
                boolean estDe = rs.getBoolean("estDe");
                String userEns = rs.getString("userEns");
                try (Connection con2 = getConnection();
                        PreparedStatement st2 = con2.prepareStatement("SELECT * FROM Utilisateur WHERE email= ?")) {
                            st2.setString(1, userEns);
                            try (ResultSet rs2 = st2.executeQuery()) {
                                if (rs2.next()) {
                                    String motDePasse = rs2.getString("motDePasse");
                                    String nom = rs2.getString("nom");
                                    String prenom = rs2.getString("prenom");
                                    
                                    
                                    Enseignant ens = Enseignant.getEnseignantByID(identifiant);
                                    if (ens == null) {
                                        if (estDe) {
                                            ens = new DirecteurEtudes(nom, prenom, userEns, motDePasse, identifiant);
                                        } else {
                                            ens = new Enseignant(nom, prenom, userEns, motDePasse, identifiant);
                                        }
                                        Enseignant.addEnseignant(ens);
                                    } else {
                                    }
                                    
                                    enseignants.add(ens);
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return enseignants;
    }

    @Override
    public Enseignant findByID(String identifiant) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT * FROM Enseignant WHERE identifiant= ?")) {
            st.setString(1, identifiant);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    boolean estDe = rs.getBoolean("estDe");
                    String userEns = rs.getString("userEns");
                    try (Connection con2 = getConnection();
                            PreparedStatement st2 = con2.prepareStatement("SELECT * FROM Utilisateur WHERE email= ?")) {
                                st2.setString(1, userEns);
                                try (ResultSet rs2 = st2.executeQuery()) {
                                    if (rs2.next()) {
                                        String motDePasse = rs2.getString("motDePasse");
                                        String nom = rs2.getString("nom");
                                        String prenom = rs2.getString("prenom");
                                        
                                        Enseignant ens = Enseignant.getEnseignantByID(identifiant);
                                        if (ens == null) {
                                            if (estDe) {
                                                ens = new DirecteurEtudes(nom, prenom, userEns, motDePasse, identifiant);
                                            } else {
                                                ens = new Enseignant(nom, prenom, userEns, motDePasse, identifiant);
                                            }
                                            Enseignant.addEnseignant(ens);
                                        } else {
                                        }

                                        return ens;
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
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
    public int update(Enseignant enseignant) {
        String query = "UPDATE Enseignant SET estDe= ?, userEns= ? WHERE identifiant= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setBoolean(1, enseignant instanceof DirecteurEtudes);
            st.setString(2, enseignant.getEmail());
            st.setString(3, enseignant.getIdent());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(Enseignant enseignant) {
        String query = "DELETE FROM Enseignant WHERE identifiant= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, enseignant.getIdent());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public boolean estDE(String email){
        try (Connection con = getConnection();
            PreparedStatement st = con.prepareStatement("SELECT estDe FROM Enseignant WHERE userEns= ?")) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    boolean estDe = rs.getBoolean("estDe");
                    return estDe;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
