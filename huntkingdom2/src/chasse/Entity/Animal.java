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
public enum Animal {
    Faisan_commun("Faisan commun"), Faisan_vénéré("Faisan vénéré"), Lapin_de_garenne("Lapin de garenne"), Lièvre_brun("Lièvre brun"), Perdrix_grise("Perdrix grise"), Perdrix_rouge("Perdrix rouge"),
    
    
    
    Canard_chipeau("Canard chipeau"), Canard_colvert("Canard colvert"), Foulque_macroule("Foulque macroule"), Garrot_à_œil_dor("Garrot à œil d'or"), Harelde_de_Miquelon("Harelde de Miquelon"), Macreuse_brune("Macreuse brune"), Macreuse_noire("Macreuse noire"), Fuligule_milouin("Fuligule milouin"), Fuligule_milouinan("Fuligule milouinan"), Fuligule_morillon("Fuligule morillon"), Nette_rousse("Nette rousse"), Oie_cendrée("Oie cendrée"), Oie_des_moissons("Oie des moissons"), Oie_rieuse("Oie rieuse"), Canard_pilet("Canard pilet"), Poule_deau("Poule deau"), Râle_deau("Râle deau"), Sarcelle_dété("Sarcelle d'été"), Sarcelle_dhiver("Sarcelle dhiver"), Canard_siffleur("Canard siffleur"), Canard_souchet("Canard souchet"),
    
    
    
    Corbeaux_freux("Corbeaux freux"), Corneille_noire("Corneille noire"), Geai_des_chênes("Geai des chênes"), Pie_bavarde("Pie bavarde"),
    
    
    
    Barge_rousse("Barge rousse"), Bécasseau_maubèche("Bécasseau maubèche"), Bécassine_des_marais("Bécassine des marais"), Bécassine_sourde("Bécassine sourde"), Chevalier_aboyeur("Chevalier aboyeur"), Chevalier_arlequin("Chevalier arlequin"), Chevalier_combattant("Chevalier combattant"), Chevalier_gambette("Chevalier gambette"), Courlis_corlieu("Courlis corlieu"), Huitrier_pie("Huitrier pie"), Pluvier_argenté("Pluvier argenté"), Pluvier_doré("Pluvier doré"), Vanneau_huppé("Vanneau huppé"),
    
    
    
    
    Alouette_des_champs("Alouette des champs"), Bécasse_des_bois("Bécasse des bois"), Caille_des_blés("Caille des blés"), Grive_draine("Grive draine"), Grive_litorne("Grive litorne"), Grive_mauvis("Grive mauvis"), Grive_musicienne("Grive musicienne"), Merle_noir("Merle noir"), Pigeon_biset("Pigeon biset"), Pigeon_colombin("Pigeon colombin"), Pigeon_ramier("Pigeon ramier"), Tourterelle_des_bois("Tourterelle des bois"), Tourterelle_turque("Tourterelle turque"),
    
    
    
    
    Belette("Belette"), Chien_viverrin("Chien viverrin"), Fouine("Fouine"), Hermine("Hermine"), Martre("Martre"), Putois("Putois"), Raton_laveur("Raton laveur"), Renard("Renard"), Vison_dAmérique("Vison d'Amérique"),
    
    
    
    
    Cerf("Cerf"), Chevreuil("Chevreuil"),  Chamois("Chamois"), Mouflon("Mouflon"), Sanglier("Sanglier"),
    
    
    
    
   
    
    
    
    
    Gélinotte("Gélinotte"), Grand_tétras("Grand tétras"), Lagopède_alpin("Lagopède alpin"), Lièvre_variable("Lièvre variable"), Marmotte("Marmotte"), Perdrix_bartavelle("Perdrix bartavelle"), Perdrix_de_montagne("Perdrix de montagne"), Tétras_lyre("Tétras lyre")
    
    ;
    
     private String nomanimal;
       //private   String type;
    private String url;
 
    Animal(String envUrl) {
        this.url = envUrl;
    }
 
    public String getUrl() {
        return url;
    }
    
}


