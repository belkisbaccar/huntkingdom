-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 29, 2020 at 06:31 AM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `huntkingdom`
--

-- --------------------------------------------------------

--
-- Table structure for table `annonce`
--

DROP TABLE IF EXISTS `annonce`;
CREATE TABLE IF NOT EXISTS `annonce` (
  `id_annonce` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `aime` int(100) DEFAULT NULL,
  `etat` int(100) NOT NULL,
  PRIMARY KEY (`id_annonce`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `annonce`
--

INSERT INTO `annonce` (`id_annonce`, `text`, `image`, `user_id`, `aime`, `etat`) VALUES
(11, 'fghjkkjh', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 13, 1, 1),
(12, 'ukzf', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 13, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `chasse`
--

DROP TABLE IF EXISTS `chasse`;
CREATE TABLE IF NOT EXISTS `chasse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `animal` enum('Faisan_commun','Faisan_vénéré','Lapin_de_garenne','Lièvre_brun','Perdrix_grise','Perdrix_rouge','Canard_chipeau','Canard_colvert','Foulque_macroule','Garrot_à_œil_dor','Harelde_de_Miquelon','Macreuse_brune','Macreuse_noire','Fuligule_milouin','Fuligule_milouinan','Fuligule_morillon','Nette_rousse','Oie_cendrée','Oie_des_moissons','Oie_rieuse','Canard_pilet','Poule_deau','Râle_deau','Sarcelle_dété','Sarcelle_dhiver','Canard_siffleur','Canard_souchet','Corbeaux_freux','Corneille_noire','Geai_des_chênes','Pie_bavarde','Barge_rousse','Bécasseau_maubèche','Bécassine_des_marais','Bécassine_sourde','Chevalier_aboyeur','Chevalier_arlequin','Chevalier_combattant','Chevalier_gambette','Courlis_corlieu','Huitrier_pie','Pluvier_argenté','Pluvier_doré','Vanneau_huppé','Alouette_des_champs','Bécasse_des_bois','Caille_des_blés','Grive_draine','Grive_litorne','Grive_mauvis','Grive_musicienne','Merle_noir','Pigeon_biset','Pigeon_colombin','Pigeon_ramier','Tourterelle_des_bois','Tourterelle_turque','Belette','Chien_viverrin','Fouine','Hermine','Martre','Raton_laveur','Putois','Renard','Vison_dAmérique','Cerf','Chevreuil','Chamois','Mouflon','Sanglier') NOT NULL,
  `region` varchar(20) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `type` enum('herbivore','carnivore','oiseaux','Corvidés','Petit_gibier_de_plaine','Canards_oies_et_rallides','Oiseau_de_passage','Prédateurs_terrestres','Grand_gibier','Petit_gibier_de_montagne','Autres','Limicoles') NOT NULL,
  `id_user` int(11) NOT NULL,
  `idp` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idp` (`idp`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chasse`
--

INSERT INTO `chasse` (`id`, `animal`, `region`, `date_debut`, `date_fin`, `type`, `id_user`, `idp`) VALUES
(183, 'Faisan_commun', 'op', '2020-02-03', '2020-02-25', 'Corvidés', 13, 7),
(184, 'Faisan_commun', 'opüöü', '2020-02-18', '2020-02-28', 'Canards_oies_et_rallides', 13, 7),
(185, 'Faisan_commun', 'op', '2020-02-10', '2020-02-27', 'Limicoles', 13, 7),
(186, 'Faisan_commun', 'op', '2020-02-10', '2020-02-27', 'Limicoles', 13, 7),
(187, 'Faisan_commun', 'ijdiofjd', '2020-02-25', '2020-02-25', 'Petit_gibier_de_plaine', 13, 7),
(188, 'Faisan_commun', 'ass', '2020-02-10', '2020-02-25', 'Petit_gibier_de_plaine', 13, 7),
(189, 'Faisan_commun', 'ass', '2020-02-10', '2020-02-25', 'Petit_gibier_de_plaine', 13, 7),
(190, 'Faisan_commun', 'op', '2020-02-10', '2020-02-27', 'Limicoles', 13, 7),
(191, 'Faisan_commun', 'ass', '2020-02-10', '2020-02-25', 'Petit_gibier_de_plaine', 13, 7),
(192, 'Faisan_commun', 'yyyyyyyyyyyyyyyyy', '2020-02-10', '2020-02-25', 'Petit_gibier_de_plaine', 13, 7),
(193, 'Faisan_commun', 'sssssssssss', '2020-02-03', '2020-02-25', 'Corvidés', 13, 7),
(194, 'Faisan_commun', 'walid', '2020-02-10', '2020-02-27', 'Limicoles', 13, 7),
(196, 'Lièvre_brun', 'kljfkljf', '2020-02-13', '2020-02-15', 'Corvidés', 13, 8);

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `idcmd` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix_total` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL,
  PRIMARY KEY (`idcmd`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`idcmd`, `id`, `quantite`, `prix_total`, `etat`) VALUES
(3, 3, 2, 24, 'en cours '),
(5, 13, 3, 450, 'traitée'),
(6, 13, 6, 483, 'traitée'),
(7, 13, 7, 71, 'en cours '),
(8, 13, 8, 82, 'en cours ');

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id_commentaire` int(100) NOT NULL AUTO_INCREMENT,
  `id_user` int(100) NOT NULL,
  `id_annonce` int(100) NOT NULL,
  `commentaire` varchar(255) NOT NULL,
  PRIMARY KEY (`id_commentaire`),
  KEY `id_user` (`id_user`),
  KEY `id_annonce` (`id_annonce`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commentaire`
--

INSERT INTO `commentaire` (`id_commentaire`, `id_user`, `id_annonce`, `commentaire`) VALUES
(9, 13, 12, 'kuhukh');

-- --------------------------------------------------------

--
-- Table structure for table `demande_admin`
--

DROP TABLE IF EXISTS `demande_admin`;
CREATE TABLE IF NOT EXISTS `demande_admin` (
  `id_demande` int(100) NOT NULL AUTO_INCREMENT,
  `id_user` int(100) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  PRIMARY KEY (`id_demande`),
  KEY `id_user` (`id_user`),
  KEY `nom` (`nom`),
  KEY `prenom` (`prenom`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `titre_event` varchar(50) NOT NULL,
  `image` varchar(255) NOT NULL,
  `date_debut_event` date NOT NULL,
  `date_fin_event` date NOT NULL,
  `nb_place_event` int(11) NOT NULL,
  PRIMARY KEY (`id_event`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id_event`, `titre_event`, `image`, `date_debut_event`, `date_fin_event`, `nb_place_event`) VALUES
(2, 'gr', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', '2020-01-27', '2020-02-09', 10),
(3, 'walid', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', '2020-01-27', '2020-02-19', 126),
(5, 'ggh', 'file:/D:/lightroom/cat-3.jpg', '2020-01-27', '2020-02-25', 150),
(8, 'walid', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', '2020-01-27', '2020-02-19', 12),
(10, 'hdhdhjd', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', '2020-02-14', '2020-02-21', 42);

-- --------------------------------------------------------

--
-- Table structure for table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `idp` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `nomP` varchar(255) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  PRIMARY KEY (`idp`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `panier`
--

INSERT INTO `panier` (`idp`, `id`, `nomP`, `quantite`, `prix`) VALUES
(8, 13, 'tent', 1, 5),
(7, 13, 'gjlk', 7, 77);

-- --------------------------------------------------------

--
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `id_participation` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `id_event` int(11) NOT NULL,
  `date_reservation` date NOT NULL,
  PRIMARY KEY (`id_participation`),
  KEY `username` (`username`),
  KEY `id_event` (`id_event`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participation`
--

INSERT INTO `participation` (`id_participation`, `username`, `id_event`, `date_reservation`) VALUES
(7, 'user', 2, '2020-02-26'),
(9, 'user', 3, '2020-02-26'),
(10, 'aziz', 8, '2020-02-28'),
(11, 'aziz', 5, '2020-02-28'),
(12, 'aziz', 3, '2020-02-28'),
(13, 'aziz', 2, '2020-02-28');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `image` varchar(255) NOT NULL,
  `prix_promo` float NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ID`, `nom`, `quantite`, `prix`, `image`, `prix_promo`) VALUES
(7, 'gjlk', 0, 11, 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 0),
(8, 'tent', 4, 5, 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id_promotion` int(11) NOT NULL AUTO_INCREMENT,
  `id_produit` int(11) NOT NULL,
  `taux` float NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  PRIMARY KEY (`id_promotion`),
  KEY `id_produit` (`id_produit`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `publicite`
--

DROP TABLE IF EXISTS `publicite`;
CREATE TABLE IF NOT EXISTS `publicite` (
  `id_publicite` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `nom_proprietaire` varchar(25) NOT NULL,
  `prix` float NOT NULL,
  PRIMARY KEY (`id_publicite`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publicite`
--

INSERT INTO `publicite` (`id_publicite`, `nom`, `image`, `date_debut`, `date_fin`, `nom_proprietaire`, `prix`) VALUES
(63, 'walid', 'file:/D:/lightroom/247.jpg', '2020-01-27', '2020-02-08', 'jj', 10),
(64, 'walid', 'file:/D:/lightroom/247.jpg', '2020-01-27', '2020-02-08', 'jj', 10),
(67, 'vfdbvf', 'file:/D:/lightroom/76605236_2133901326904910_8052490271801212928_n.jpg', '2020-01-27', '2020-02-10', 'cv', 82),
(75, 'gfsd', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', '2020-01-27', '2020-02-04', 'sdj', 20);

-- --------------------------------------------------------

--
-- Table structure for table `publicite_aimer`
--

DROP TABLE IF EXISTS `publicite_aimer`;
CREATE TABLE IF NOT EXISTS `publicite_aimer` (
  `id_pub_aimer` int(11) NOT NULL AUTO_INCREMENT,
  `id_publicite` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id_pub_aimer`),
  KEY `id_publicite` (`id_publicite`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publicite_aimer`
--

INSERT INTO `publicite_aimer` (`id_pub_aimer`, `id_publicite`, `id_user`, `date`) VALUES
(64, 63, 3, '2020-02-26'),
(65, 64, 3, '2020-02-26'),
(66, 67, 3, '2020-02-26'),
(68, 63, 4, '2020-02-27'),
(69, 64, 4, '2020-02-27'),
(70, 63, 13, '2020-02-28'),
(71, 64, 13, '2020-02-28');

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id_reclamation` int(10) NOT NULL AUTO_INCREMENT,
  `etat` varchar(25) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `note` int(1) NOT NULL,
  `id_annonce_reclame` int(10) DEFAULT NULL,
  `id_user` int(10) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `id_produit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_reclamation`),
  KEY `id_user` (`id_user`),
  KEY `id_produit` (`id_produit`),
  KEY `id_annonce_reclame` (`id_annonce_reclame`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `etat`, `description`, `image`, `note`, `id_annonce_reclame`, `id_user`, `date`, `id_produit`) VALUES
(44, 'Non traitée', 'hello wadou', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 5, 11, 13, '2020-02-28', NULL),
(45, 'Traitée', 'hebebebbe', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 0, NULL, 13, '2020-02-28', 7),
(46, 'En cours', 'reclqme produit', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 0, NULL, 13, '2020-02-28', 7),
(47, 'Non traitée', 'helooo', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 0, 11, 13, '2020-02-28', NULL),
(48, 'non traite', 'hhnn', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 0, NULL, 13, '2020-02-28', 7),
(49, 'Non traitée', 'hbzunad', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 0, 12, 13, '2020-02-28', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(100) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `age` int(100) NOT NULL,
  `sexe` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `telephone` int(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nom`, `prenom`, `age`, `sexe`, `mot_de_passe`, `photo`, `role`, `username`, `telephone`, `email`) VALUES
(3, 'walid', 'tayech', 17, 'homme', '123456789', 'file:/D:/lightroom/247.jpg', 'administrateur', 'user', 21722423, 'walid.tayech@esprit.tn'),
(4, 'walid', 'tayeche', 17, 'h', '123456789', 'file:/D:/lightroom/247.jpg', 'client', 'user1', 123456789, 'jjfmjjk'),
(13, 'aziz', 'mouhli', 0, 'homme', '123456789', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 'Client', 'aziz', 238318588, 'walid.tayeche@esprit.tn'),
(15, 'abdou', 'jazi', 21, 'Homme', '123456789', 'file:/D:/lightroom/cat-3.jpg', 'administrateur', 'abdou', 21951870, 'abdoujazi@esprit.tn'),
(16, 'walidou', 'baba', 21, 'homme', '123456789', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 'administrateur', 'wadou123', 21951872, 'walid.tayechi@esprit.tn'),
(17, 'jazi', 'abdou', 21, 'homme', '123456789', 'file:/C:/Users/walid/OneDrive/Pictures/IMG_0298-3.jpg', 'administrateur', 'abdou123', 21985321, 'abdelhamid.jazi@sprit.tn');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `annonce`
--
ALTER TABLE `annonce`
  ADD CONSTRAINT `annonce_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `chasse`
--
ALTER TABLE `chasse`
  ADD CONSTRAINT `chasse_ibfk_1` FOREIGN KEY (`idp`) REFERENCES `product` (`ID`);

--
-- Constraints for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `commentaire_ibfk_2` FOREIGN KEY (`id_annonce`) REFERENCES `annonce` (`id_annonce`);

--
-- Constraints for table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `participation_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `participation_ibfk_2` FOREIGN KEY (`id_event`) REFERENCES `evenement` (`id_event`);

--
-- Constraints for table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `promotion_ibfk_1` FOREIGN KEY (`id_produit`) REFERENCES `product` (`ID`);

--
-- Constraints for table `publicite_aimer`
--
ALTER TABLE `publicite_aimer`
  ADD CONSTRAINT `publicite_aimer_ibfk_1` FOREIGN KEY (`id_publicite`) REFERENCES `publicite` (`id_publicite`),
  ADD CONSTRAINT `publicite_aimer_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`id_annonce_reclame`) REFERENCES `annonce` (`id_annonce`),
  ADD CONSTRAINT `reclamation_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `reclamation_ibfk_3` FOREIGN KEY (`id_produit`) REFERENCES `product` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
