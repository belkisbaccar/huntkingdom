<?php

namespace HuntkingdomBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * PubliciteAimer
 *
 * @ORM\Table(name="publicite_aimer", indexes={@ORM\Index(name="id_publicite", columns={"id_publicite"}), @ORM\Index(name="id_user", columns={"id_user"})})
 * @ORM\Entity
 */
class PubliciteAimer
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
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */
    public function setDate($date)
    {
        $this->date = $date;
    }
    /**
     * @return int
     */
    public function getIdPubAimer()
    {
        return $this->idPubAimer;
    }

    /**
     * @param int $idPubAimer
     */
    public function setIdPubAimer($idPubAimer)
    {
        $this->idPubAimer = $idPubAimer;
    }
    /**
     * @var integer
     *
     * @ORM\Column(name="id_pub_aimer", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPubAimer;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_publicite", type="integer", nullable=false)
     */
    private $idPublicite;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false)
     */
    private $idUser;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;


}

