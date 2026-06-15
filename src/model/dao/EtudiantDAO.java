package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import model.entity.Etudiant;
import model.entity.Promotion;

public class EtudiantDAO extends DAO<Etudiant, Integer> {


    public Connection isConnected() throws SQLException {
        return getConnection();
    }

    @Override
    public int create(Etudiant etudiant) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("INSERT INTO Etudiant (numero, userEtud, groupeTD, promo) VALUES (?, ?, ?, ?)")) {
            st.setInt(1, etudiant.getNum());
            st.setString(2, etudiant.getEmail());
            st.setString(3, etudiant.getGroupe().getNom());
            st.setString(4, etudiant.getGroupe().getPromotion().getNomPromo().toString());
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
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new LinkedList<>();
        try (Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Etudiant")) {
            while (rs.next()) {
                int numero = rs.getInt("numero");
                String parcours = rs.getString("parcours");
                String userEtud = rs.getString("userEtud");
                String groupeTD = rs.getString("groupeTD");
                String promo = rs.getString("promo");
                try (Connection con2 = getConnection();
                        PreparedStatement st2 = con2.prepareStatement("SELECT * FROM Utilisateur WHERE email= ?")) {
                            st2.setString(1, userEtud);
                            try (ResultSet rs2 = st2.executeQuery()) {
                                if (rs2.next()) {
                                    String motDePasse = rs2.getString("motDePasse");
                                    String nom = rs2.getString("nom");
                                    String prenom = rs2.getString("prenom");
                                    Etudiant oldEtud = Etudiant.getEtudiantById(numero);
                                    Etudiant newEtud = null;
                                    if (oldEtud == null) {
                                        newEtud = new Etudiant(nom, prenom, userEtud, motDePasse, numero);
                                        Etudiant.addEtudiant(newEtud);
                                    } else {
                                        oldEtud.setNom(nom);
                                        oldEtud.setPrenom(prenom);
                                        oldEtud.setEmail(userEtud);
                                        oldEtud.setMdp(motDePasse);
                                        newEtud = oldEtud;
                                    }
                                    Promotion.getPromotionByName(promo).getGroupeByName(groupeTD).ajouterEtudiant(newEtud);
                                    etudiants.add(newEtud);
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
        return etudiants;
    }

    @Override
    public Etudiant findByID(Integer numero) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT * FROM Etudiant WHERE numero= ?")) {
            st.setInt(1, numero);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String parcours = rs.getString("parcours");
                    String userEtud = rs.getString("userEtud");
                    String groupeTD = rs.getString("groupeTD");
                    String promo = rs.getString("promo");
                    try (Connection con2 = getConnection();
                            PreparedStatement st2 = con2.prepareStatement("SELECT * FROM Utilisateur WHERE email= ?")) {
                                st2.setString(1, userEtud);
                                try (ResultSet rs2 = st2.executeQuery()) {
                                    if (rs2.next()) {
                                        String motDePasse = rs2.getString("motDePasse");
                                        String nom = rs2.getString("nom");
                                        String prenom = rs2.getString("prenom");

                                        Etudiant oldEtud = Etudiant.getEtudiantById(numero);
                                        Etudiant newEtud = null;
                                        if (oldEtud == null) {
                                            newEtud = new Etudiant(nom, prenom, userEtud, motDePasse, numero);
                                            Etudiant.addEtudiant(newEtud);
                                        } else {
                                            oldEtud.setNom(nom);
                                            oldEtud.setPrenom(prenom);
                                            oldEtud.setEmail(userEtud);
                                            oldEtud.setMdp(motDePasse);
                                            newEtud = oldEtud;
                                        }
                                        Promotion.getPromotionByName(promo).getGroupeByName(groupeTD).ajouterEtudiant(newEtud);
                                        return newEtud;
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
    public int update(Etudiant etudiant) {
        String query = "UPDATE Etudiant SET userEtud= ?, groupeTD= ?, promo= ? WHERE numero= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, etudiant.getEmail());
            st.setString(2, etudiant.getGroupe().getNom());
            st.setString(3, etudiant.getGroupe().getPromotion().getNomPromo().toString());
            st.setInt(4, etudiant.getNum());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(Etudiant etudiant) {
        String query = "DELETE FROM Etudiant WHERE numero= ?";
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, etudiant.getNum());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int findIDByMail(String mail){
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT numero FROM Etudiant WHERE userEtud= ?")) {
            st.setString(1, mail);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int numEtud = rs.getInt("numero");
                    return numEtud;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
