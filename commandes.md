Commandes pour compiler et exécuter notre projet Scodoc :

Compilation :
javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -d class -sourcepath `find . -name '*.java'`

Exécution :
java -cp class --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml view.SkodocMain
