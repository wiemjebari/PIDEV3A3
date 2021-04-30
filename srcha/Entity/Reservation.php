<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use phpDocumentor\Reflection\Types\Integer;

/**
 * Reservation
 *
 * @ORM\Table(name="reservation_centre", indexes={@ORM\Index(name="fkey", columns={"id_centre"})})
 * @ORM\Entity
 */
class Reservation
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

    /**
     * @var \Centre
     *
     * @ORM\ManyToOne(targetEntity="Centre")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_centre", referencedColumnName="id")
     * })
     */
    private $idCentre;

    public function getIdReservation(): ?int
    {
        return $this->idReservation;
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

    public function getIdCentre(): ?Centre
    {
        return $this->idCentre;
    }

    public function setIdCentre(?Centre $idCentre): self
    {
        $this->idCentre = $idCentre;

        return $this;
    }


}
