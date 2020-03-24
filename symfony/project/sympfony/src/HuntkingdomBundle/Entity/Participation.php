<?php

namespace HuntkingdomBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Participation
 *
 * @ORM\Table(name="participation", indexes={@ORM\Index(name="username", columns={"username"}), @ORM\Index(name="id_event", columns={"id_event"})})
 * @ORM\Entity
 */
class Participation
{
    /**
     * @return string
     */
    public function getUsername()
    {
        return $this->username;
    }

    /**
     * @param string $username
     */
    public function setUsername($username)
    {
        $this->username = $username;
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
     * @return \DateTime
     */
    public function getDateReservation()
    {
        return $this->dateReservation;
    }

    /**
     * @param \DateTime $dateReservation
     */
    public function setDateReservation($dateReservation)
    {
        $this->dateReservation = $dateReservation;
    }
    /**
     * @return int
     */
    public function getIdParticipation()
    {
        return $this->idParticipation;
    }

    /**
     * @param int $idParticipation
     */
    public function setIdParticipation($idParticipation)
    {
        $this->idParticipation = $idParticipation;
    }
    /**
     * @var integer
     *
     * @ORM\Column(name="id_participation", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idParticipation;

    /**
     * @var string
     *
     * @ORM\Column(name="username", type="string", length=255, nullable=false)
     */
    private $username;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_event", type="integer", nullable=false)
     */
    private $idEvent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_reservation", type="date", nullable=false)
     */
    private $dateReservation;


}

