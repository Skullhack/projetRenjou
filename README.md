# projetRenjou

#PROCEDURE DE CREATION D'UN PROJET GITHUB

#Methode non détaillé
- il faut en premier créer le repository sur github
- importer le repo sur eclipse et selectionner "import using new project wizard"
- ensuite dans l arborescence du nouveau projet, ce mettre à la racine du depot github
- creer
- commiter
- enjoy


#Méthode détaillé
- Sur github -> New repository -> mettre un nom -> create repository

- Sur un terminal, mettez vous dans un dossier ou vous voulez importer le git (par exemple MON_DOSSIER_TEST)

- Efectuer un git clone avec l'url du repository git par exemple : "git clone https://github.com/arcaman/testRepo.git"

effectuer la suite de commandes données sur git : 

echo "# testRepo" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/arcaman/testRepo.git
git push -u origin master

renseigner votre login et mot de passe -> Entrer

Ensuite sur java, récupérer le repository git.

Pour cela dans "Package explorer" -> Clic droit Import 

selectionner git -> projects from git -> clone URI -> specifier l'url du repository en HTTPS (https://github.com/arcaman/testRepo) -> (renseigner le mot de passe si demandé) -> branche master.

Sur l'écran "Select a wizard to use for importing projects" -> cliquer sur le bouton "Import using the New Project Wizard" -> Finish

Nommer le projet (exemple "projetTestJava") -> Décocher "Use defaut location" -> Et selectionner l'arborescence pour aller jusqu'au projet git (dans cet exemple, user/MON_DOSSIER_TEST/testRepo -> Valider -> Finish


#PROCEDURE DE RECUPERATION D'UN PROJET GITHUB

Dans le menu "package explorer" -> clic droit -> Import -> Projects from git -> clone URI -> selectionner l'url du repository à récupérer (dans cet exemple "https://github.com/arcaman/TESTAPPLITUTO") -> next -> next -> selectionner la destination locale (dans cet exemple "mon_nom/RECUPTUTO" -> valider -> next -> selectionner "Import as general project" -> next -> cancel

A ce stade, on a récupéré le repository mais pas encore le dossier de travail

Pour cela dans package explorer -> clic droit -> import -> "existing projects into workspace" -> dans "select root directory", selectionner la destination locale précédente lors de la récupération du repository (dans cet exemple l'url sera "mon_nom/RECUPTUTO") -> valider -> finish

===> Maintenant, le projet apparaît dans la barre "package explorer" et il est possible d'effectuer des commits and push
