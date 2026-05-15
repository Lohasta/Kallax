public class Etudiant extends Utilisateur{
    private int numEtudiant;

    public Etudiant(String nom, String prenom, String email, String mdp, int num){
        super(nom, prenom, email, mdp);

        if(num != 0){
            this.numEtudiant = num;
        } else{
            System.out.println("Constructeur de Etudiant : numero incorrect.");
        }
    }

    public String toString(){
        return ("\n*** Etudiant ***\n" + super.toString() + "\nn° Etudiant : " + this.getNum());
    }

    public int getNum(){
        return this.numEtudiant;
    }

    public void setNum(int nvNum){
        this.numEtudiant = nvNum;
    }
}
