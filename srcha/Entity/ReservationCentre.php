<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * ReservationCentre
 *
 * @ORM\Table(name="reservation_centre")
 * @ORM\Entity
 */
class ReservationCentre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_reservation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idReservation;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_centre", type="string", length=255, nullable=false)
     */
    private $nomCentre;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="services", type="string", length=255, nullable=false)
     */
    private $services;

    /**
     * @var int
     *
     * @ORM\Column(name="prix_service", type="integer", nullable=false)
     */
    private $prixService;

    public function getIdReservation(): ?int
    {
        return $this->idReservation;
    }

    public function getNomCentre(): ?string
    {
        return $this->nomCentre;
    }

    public function setNomCentre(string $nomCentre): self
    {
        $this->nomCentre = $nomCentre;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getServices(): ?string
    {
        return $this->services;
    }

    public function setServices(string $services): self
    {
        $this->services = $services;

        return $this;
    }

    public function getPrixService(): ?int
    {
        return $this->prixService;
    }

    public function setPrixService(int $prixService): self
    {
        $this->prixService = $prixService;

        return $this;
    }


}
