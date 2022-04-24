package modele1;

public class Artifact extends Objet {

    //Constructeur
    /**Artifact() :
     * 		Element e : un element parmi un type enumere.
     * 		Definit e comme etant l'element de l'Artifact.
     **/
    public Artifact(Element e) {
        super(e);
    }

    //Methodes
    /**boolean ArtifactEgal() :
     *  	Artifact a : un Artifact.
     * 		Return : True si l'Artifact donne en parametre a le meme element que l'Artifact considere, false sinon.
     **/
    public boolean ArtifactEgal(Artifact a) {
        return this.e == a.e;
    }
}
