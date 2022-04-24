package Controller;

public class Playeractions {
    Playermovement deplPlayer;
import Modeless.Island;


import Controller.Playermovement;

    public class ActionsPlayer{
        private Playermovement deplPlayer;
        private Island Island;
        //RecupererArtefact recupArtefact;

        public ActionsPlayer(Island Island) {
            this.Island = Island;
            this.deplPlayer = new Playermovement(Island);
        }

    }
