<?php

namespace HuntkingdomBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Chasse
 *
 * @ORM\Table(name="chasse", indexes={@ORM\Index(name="idp", columns={"idp"}), @ORM\Index(name="id_user", columns={"id_user"})})
 * @ORM\Entity
 */
class Chasse
{
    /**
     * @return string
     */
    public function getAnimal()
    {
        return $this->animal;
    }

    /**
     * @param string $animal
     */
    public function setAnimal($animal)
    {
        $this->animal = $animal;
    }

    /**
     * @return string
     */
    public function getRegion()
    {
        return $this->region;
    }

    /**
     * @param string $region
     */
    public function setRegion($region)
    {
        $this->region = $region;
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
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

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
     * @return Product
     */
    public function getIdp()
    {
        return $this->idp;
    }

    /**
     * @param Product $idp
     */
    public function setIdp($idp)
    {
        $this->idp = $idp;
    }
    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="animal", type="string", nullable=false)
     */
    private $animal;

    /**
     * @var string
     *
     * @ORM\Column(name="region", type="string", length=20, nullable=false)
     */
    private $region;

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

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", nullable=false)
     */
    private $type;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false)
     */
    private $idUser;

    /**
     * @var \HuntkingdomBundle\Entity\Product
     *
     * @ORM\ManyToOne(targetEntity="HuntkingdomBundle\Entity\Product")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idp", referencedColumnName="ID")
     * })
     */
    private $idp;


}

