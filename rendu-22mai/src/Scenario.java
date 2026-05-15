public class Scenario{
    public static void main(String[] args){
        Utilisateur u1 = new Etudiant("Lohan", "Kerrault", "lk@ubs.fr", "1234lol", 1);
        Utilisateur u2 = new Etudiant(null, null, null, null, 0);

        System.out.println(u1);
        System.out.println(u2);

        Utilisateur u3 = new Enseignant("Sahur", "Tung", "tsahur@ubs.fr", "5678mdr", "TS");
        Utilisateur u4 = new Enseignant(null, null, null, null, null);

        System.out.println(u3);
        System.out.println(u4);

        Utilisateur u5 = new DirecteurEtudes("Crocodillo", "Bombardillo", "bcrocs@ubs.fr", "9009jsp", "BC");
        Utilisateur u6 = new DirecteurEtudes(null, null, null, null, null);

        System.out.println(u5);
        System.out.println(u6);
    }
}
