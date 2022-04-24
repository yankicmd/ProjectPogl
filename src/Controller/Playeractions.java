package Controller;

import Modelesss.Island;


import Controller.Playermovement;

    public class Playeractions{
        private Playermovement deplPlayer;
        private Island Island;
        //RecupererArtifact recupArtifact;

        public Playeractions(Island Island) {
            this.Island = Island;
            this.deplPlayer = new Playermovement(Island);
        }

    }
