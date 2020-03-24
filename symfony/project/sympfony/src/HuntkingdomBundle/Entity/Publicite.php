<?php

namespace HuntkingdomBundle\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Publicite
 *
 * @ORM\Table(name="publicite")
 * @ORM\Entity
 */
class Publicite
{
    /**
     * @return int
     */
    public function getIdPublicite()
    {
        return $this->idPublicite;
    }

    /**
     * @param int $idPublicite
     */
    public function setIdPublicite($idPublicite)
    {
        $this->idPublicite = $idPublicite;
    }
    /**
     * @var integer
     *
     * @ORM\Column(name="id_publicite", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPublicite;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @return \DateTime
     */
    public function getDateDebut()
    {
        return $this->dateDebut;
    }

    /**
     * @return \DateTime
     */
    public function getDateFin()
    {
        return $this->dateFin;
    }

    /**
     * @return string
     */
    public function getNomProprietaire()
    {
        return $this->nomProprietaire;
    }

    /**
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;
    /**
     * @Assert\File(maxSize="1000k")
     */
    public $file;


    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut", type="date", nullable=false)
     */
    private $dateDebut;

    /**
     * @param string $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * Set image
     * @param string $image
     *
     * @return Categorie
     */
    public function setImage($image)
    {
        $this->image = $image;
    }
    public function getWebPatch(){
        return null===$this->image ? null : $this->getUploadDir.'/'.$this->image;
    }
    protected function getUploadRootDir(){
        return __DIR__.'/../../../web/'.$this->getUploadDir();
    }
    protected function getUploadDir(){
        return 'imagesPub';
    }
    public function uploadPubimage(){
        if($this->file ===null)
        {
            return;
        }
        else {
            $this->file->move($this->getUploadRootDir(), $this->file->getClientOriginalName());
            $this->image = $this->file->getClientOriginalName();
            $this->file = null;

        }
    }
    /**
     * @param \DateTime $dateDebut
     */
    public function setDateDebut($dateDebut)
    {
        $this->dateDebut = $dateDebut;
    }

    /**
     * @param \DateTime $dateFin
     */
    public function setDateFin($dateFin)
    {
        $this->dateFin = $dateFin;
    }

    /**
     * @param string $nomProprietaire
     */
    public function setNomProprietaire($nomProprietaire)
    {
        $this->nomProprietaire = $nomProprietaire;
    }

    /**
     * @param float $prix
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;
    }

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_fin", type="date", nullable=false)
     */
    private $dateFin;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_proprietaire", type="string", length=25, nullable=false)
     */
    private $nomProprietaire;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;


}

