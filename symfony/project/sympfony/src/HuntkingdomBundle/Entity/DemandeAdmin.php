<?php

namespace HuntkingdomBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * DemandeAdmin
 *
 * @ORM\Table(name="demande_admin", indexes={@ORM\Index(name="id_user", columns={"id_user"}), @ORM\Index(name="nom", columns={"nom"}), @ORM\Index(name="prenom", columns={"prenom"})})
 * @ORM\Entity
 */
class DemandeAdmin
{
    /**
     * @return int
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * @param int $idUser
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;
    }

    /**
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return string
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * @param string $prenom
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }
    /**
     * @return int
     */
    public function getIdDemande()
    {
        return $this->idDemande;
    }

    /**
     * @param int $idDemande
     */
    public function setIdDemande($idDemande)
    {
        $this->idDemande = $idDemande;
    }
    /**
     * @var integer
     *
     * @ORM\Column(name="id_demande", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idDemande;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false)
     */
    private $idUser;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=255, nullable=false)
     */
    private $prenom;


}

