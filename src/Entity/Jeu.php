<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\JeuRepository;
use Doctrine\ORM\Mapping as ORM;


/**
 * @ApiResource()
 * @ORM\Entity(repositoryClass=JeuRepository::class)
 */
class Jeu
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $id_client;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nom_cat;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nom_jeu;

    /**
     * @ORM\Column(type="integer")
     */
    private $niv_diff;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdClient(): ?int
    {
        return $this->id_client;
    }

    public function setIdClient(?int $id_client): self
    {
        $this->id_client = $id_client;

        return $this;
    }

    public function getNomCat(): ?string
    {
        return $this->nom_cat;
    }

    public function setNomCat(string $nom_cat): self
    {
        $this->nom_cat = $nom_cat;

        return $this;
    }

    public function getNomJeu(): ?string
    {
        return $this->nom_jeu;
    }

    public function setNomJeu(string $nom_jeu): self
    {
        $this->nom_jeu = $nom_jeu;

        return $this;
    }

    public function getNivDiff(): ?int
    {
        return $this->niv_diff;
    }

    public function setNivDiff(int $niv_diff): self
    {
        $this->niv_diff = $niv_diff;

        return $this;
    }
}
