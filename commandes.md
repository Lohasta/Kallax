Commandes pour compiler et exécuter notre projet Scodoc :

Compilation :
javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -d class -sourcepath src `find . -name '*.java'`

Exécution :
java -cp "class:lib/mysql-connector-j-9.7.0.jar" --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml view.SkodocMain

Copie des fxml pour exécuter :
cp -r src/view/*.fxml class/view/