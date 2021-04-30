<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;

/**
 * Randonnees
 *
 * @ORM\Table(name="randonnees")
 * @ORM\Entity
 */
class Randonnees
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     * @Assert\Date()
     * @Assert\GreaterThan("today",message="la date doit etre supérieure à la date actuelle")
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="lieu", type="string", length=255, nullable=false)
     */
    private $lieu;

    /**
     * @var string
     *
     * @ORM\Column(name="heure_depart", type="string", length=255, nullable=false)
     */
    private $heureDepart;

    /**
     * @var string
     *
     * @ORM\Column(name="heure_retour", type="string", length=255, nullable=false)
     */
    private $heureRetour;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_places", type="integer", nullable=false)
     */
    private $nbrPlaces;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=false)
     */
    private $photo;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */


    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }


    public function getLieu(): ?string
    {
        return $this->lieu;
    }

    public function setLieu(string $lieu): self
    {
        $this->lieu = $lieu;

        return $this;
    }

    public function getHeureDepart(): ?string
    {
        return $this->heureDepart;
    }

    public function setHeureDepart(string $heureDepart): self
    {
        $this->heureDepart = $heureDepart;

        return $this;
    }

    public function getHeureRetour(): ?string
    {
        return $this->heureRetour;
    }

    public function setHeureRetour(string $heureRetour): self
    {
        $this->heureRetour = $heureRetour;

        return $this;
    }

    public function getNbrPlaces(): ?int
    {
        return $this->nbrPlaces;
    }

    public function setNbrPlaces(int $nbrPlaces): self
    {
        $this->nbrPlaces = $nbrPlaces;

        return $this;
    }

    public function getPhoto(): ?string
    {
        return $this->photo;
    }

    public function setPhoto(string $photo): self
    {
        $this->photo = $photo;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }



}
