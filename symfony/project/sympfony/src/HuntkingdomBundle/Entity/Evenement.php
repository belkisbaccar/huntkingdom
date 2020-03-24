<?php

namespace HuntkingdomBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Evenement
 *
 * @ORM\Table(name="evenement")
 * @ORM\Entity
 */
class Evenement
{
    /**
     * @return string
     */
    public function getTitreEvent()
    {
        return $this->titreEvent;
    }

    /**
     * @param string $titreEvent
     */
    public function setTitreEvent($titreEvent)
    {
        $this->titreEvent = $titreEvent;
    }

    /**
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param string $image
     */
    public function setImage($image)
    {
        $this->image = $image;
    }

    /**
     * @return \DateTime
     */
    public function getDateDebutEvent()
    {
        return $this->dateDebutEvent;
    }

    /**
     * @param \DateTime $dateDebutEvent
     */
    public function setDateDebutEvent($dateDebutEvent)
    {
        $this->dateDebutEvent = $dateDebutEvent;
    }

    /**
     * @return \DateTime
     */
    public function getDateFinEvent()
    {
        return $this->dateFinEvent;
    }

    /**
     * @param \DateTime $dateFinEvent
     */
    public function setDateFinEvent($dateFinEvent)
    {
        $this->dateFinEvent = $dateFinEvent;
    }

    /**
     * @return int
     */
    public function getNbPlaceEvent()
    {
        return $this->nbPlaceEvent;
    }

    /**
     * @param int $nbPlaceEvent
     */
    public function setNbPlaceEvent($nbPlaceEvent)
    {
        $this->nbPlaceEvent = $nbPlaceEvent;
    }
    /**
     * @return int
     */
    public function getIdEvent()
    {
        return $this->idEvent;
    }

    /**
     * @param int $idEvent
     */
    public function setIdEvent($idEvent)
    {
        $this->idEvent = $idEvent;
    }
    /**
     * @var integer
     *
     * @ORM\Column(name="id_event", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idEvent;

    /**
     * @var string
     *
     * @ORM\Column(name="titre_event", type="string", length=50, nullable=false)
     */
    private $titreEvent;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut_event", type="date", nullable=false)
     */
    private $dateDebutEvent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_fin_event", type="date", nullable=false)
     */
    private $dateFinEvent;

    /**
     * @var integer
     *
     * @ORM\Column(name="nb_place_event", type="integer", nullable=false)
     */
    private $nbPlaceEvent;


}

