<?php

namespace HuntkingdomBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Promotion
 *
 * @ORM\Table(name="promotion", indexes={@ORM\Index(name="id_produit", columns={"id_produit"})})
 * @ORM\Entity
 */
class Promotion
{
    /**
     * @return int
     */
    public function getIdProduit()
    {
        return $this->idProduit;
    }

    /**
     * @param int $idProduit
     */
    public function setIdProduit($idProduit)
    {
        $this->idProduit = $idProduit;
    }

    /**
     * @return float
     */
    public function getTaux()
    {
        return $this->taux;
    }

    /**
     * @param float $taux
     */
    public function setTaux($taux)
    {
        $this->taux = $taux;
    }

    /**
     * @return \DateTime
     */
    public function getDateDebut()
    {
        return $this->dateDebut;
    }

    /**
     * @param \DateTime $dateDebut
     */
    public function setDateDebut($dateDebut)
    {
        $this->dateDebut = $dateDebut;
    }

    /**
     * @return \DateTime
     */
    public function getDateFin()
    {
        return $this->dateFin;
    }

    /**
     * @param \DateTime $dateFin
     */
    public function setDateFin($dateFin)
    {
        $this->dateFin = $dateFin;
    }
    /**
     * @return int
     */
    public function getIdPromotion()
    {
        return $this->idPromotion;
    }

    /**
     * @param int $idPromotion
     */
    public function setIdPromotion($idPromotion)
    {
        $this->idPromotion = $idPromotion;
    }
    /**
     * @var integer
     *
     * @ORM\Column(name="id_promotion", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPromotion;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_produit", type="integer", nullable=false)
     */
    private $idProduit;

    /**
     * @var float
     *
     * @ORM\Column(name="taux", type="float", precision=10, scale=0, nullable=false)
     */
    private $taux;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut", type="date", nullable=false)
     */
    private $dateDebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_fin", type="date", nullable=false)
     */
    private $dateFin;


}

