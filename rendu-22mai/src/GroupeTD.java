import java.util.ArrayList;

public class GroupeTD {
    
    private String nom;
    private Promotion promotion;
    private ArrayList<Module> modulesSuivis;
    private ArrayList<Etudiant> etudiants;

    public GroupeTD(String nom,Promotion promo){
        if (nom!=null && nom!=""){
            this.nom = nom;
        }
        if(promo!=null){
            this.promotion = promo;
        }
        this.modulesSuivis=new ArrayList<>();
        this.etudiants=new Arraylist<>();
    }

    public String toString(){
        String res = "Nom : "+this.nom+"\nPromotion : "+this.promotion+"\nModulesSuivis : "+this.modulesSuivis+"\nEtudiants : "+this.etudiants+"\n";
        return res;
    }

    public void ajouterEtudiant(Etudiant e){
        if(this.etudiants.size() <= 28 && this.etudiants != null){
            this.etudiants.add(e);
        }
    }

    public void ajouterModule(Module m){
        if(this.modulesSuivis != null){
            this.modulesSuivis.add(m);
        }
    }

    public boolean equals(GroupeTD grp){
        boolean res = false;
        if(this.nom == grp.getNom() && this.promotion == grp.getPromotion()){
            res = true;
        }
        return res;
    }


    public String getNom(){
        return this.nom;
    }

    public void setNom(String n){
       if (n!=null && n!=""){
            this.nom = n;
        } 
    }

    public Promotion getPromotion(){
        return this.promotion;
    }

    public void setPromotion(Promotion promo){
        if(promo!=null){
            this.promotion = promo;
        }
    }

    public ArrayList<Module> getModulesSuivis(){
        return new ArrayList<>(this.modulesSuivis);
    }

    public void setModulesSuivis(ArrayList<Module> m){
        if(m != null){
            this.modulesSuivis = m;
        }
    }

    public ArrayList<Etudiant> getEtudiants(){
        return new ArrayList<>(this.etudiants);
    }

    public void setEtudiants(ArrayList<Etudiant> e){
        if(e != null && e.size() <= 28){
            this.etudiants = e;
        }
    }
}
