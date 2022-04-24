package Modeles;

public class Artifact extends Objet {

    public Artifact(Element e) {
        super(e);
    }

    public boolean ArtifactEgal(Artifact a) {
        return this.e == a.e;
    }
}
