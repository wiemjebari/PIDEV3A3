<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Reservationrandonnees
 *
 * @ORM\Table(name="reservationrandonnees", indexes={@ORM\Index(name="reservationrandonnees_ibfk_1", columns={"id_user"}), @ORM\Index(name="id_randonnee", columns={"id_randonnee"})})
 * @ORM\Entity
 */
class Reservationrandonnees
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
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255, nullable=false)
     */
    private $etat;

    /**
     * @var int
     * @Assert\Type(
     *     type="integer",
     *     message="The value {{ value }} is not a valid {{ type }}."
     * )
     * @ORM\Column(name="nbr_places", type="integer", nullable=false)
     */
    private $nbrPlaces;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_user", referencedColumnName="id_client")
     * })
     */
    private $User;

    /**
     * @var \Randonnees
     *
     * @ORM\ManyToOne(targetEntity="Randonnees")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_randonnee", referencedColumnName="id")
     * })
     */
    private $Randonnee;

    public function getId(): ?int
    {
        return $this->id;
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

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

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

    public function getUser(): ?User
    {
        return $this->User;
    }

    public function setUser(?User $User): self
    {
        $this->User = $User;

        return $this;
    }

    public function getRandonnee(): ?Randonnees
    {
        return $this->Randonnee;
    }

    public function setRandonnee(?Randonnees $Randonnee): self
    {
        $this->Randonnee = $Randonnee;

        return $this;
    }


}
