<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\LivreRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ApiResource()
 * @ORM\Entity(repositoryClass=LivreRepository::class)
 */
class Livre
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $titre;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $editeur;
    /**
     * @ORM\Column(type="integer")
     */
    private $categorie;
    /**
     * @ORM\Column(type="integer")
     */
    private $duree;
    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $source;
    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $audio;
    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $image;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function getEditeur(): ?string
    {
        return $this->editeur;
    }
    public function getCategorie(): ?int
    {
        return $this->categorie;
    }
    public function getDuree(): ?int
    {
        return $this->duree;
    }
    public function getSource(): ?string
    {
        return $this->source;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function getAudio(): ?string
    {
        return $this->audio;
    }



public function setTitre(?string $titre): self
    {
        $this->titre = $titre;

        return $this;
    }

    public function setEditeur(?string $editeur): self
    {
        $this->editeur = $editeur;

        return $this;
    }
    public function setCategorie(?int $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }
    public function setDuree(?int $duree): self
    {
        $this->duree = $duree;

        return $this;
    }
    public function setSource(?string $source): self
    {
        $this->source = $source;

        return $this;
    }

    public function setAudio(?string $audio): self
    {
        $this->audio = $audio;

        return $this;
    }
    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

}
