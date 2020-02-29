/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasse.Entity;

/**
 *
 * @author AHMED
 */
public enum Type_animal {
 
Petit_gibier_de_plaine("Petit gibier de plaine"), 
Canards_oies_et_rallides("Canards, oies et rallides"),
Corvidés("Corvidés"),Limicoles("Limicoles"),
Oiseau_de_passage("Oiseau de passage"),
Prédateurs_terrestres("Prédateurs terrestres"),
Grand_gibier("Grand gibier"),Autres("Autres"),
Petit_gibier_de_montagne("Petit gibier de montagne");
   private   String type;
   Type_animal(String type) { 
       this.type = type;
   }
   



  

    public String getType() {
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
////    
    
//        carnivore {
//
//                    @Override
//                    public String toString() {
//                        return "carnivore";
//                    }
//                },
//        oiseaux {
//
//                    @Override
//                    public String toString() {
//                        return "oiseaux";
//                    }
//                },
//        herbivore {
//
//                    @Override
//                    public String toString() {
//                        return "herbivore";
//                    }
//                };
}
