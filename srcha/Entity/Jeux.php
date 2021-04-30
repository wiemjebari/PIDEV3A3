<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Jeux
 *
 * @ORM\Table(name="jeux", indexes={@ORM\Index(name="id_client", columns={"id_client"})})
 * @ORM\Entity
 */
class Jeux
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
     * @var int
     *
     * @ORM\Column(name="id_client", type="integer", nullable=false)
     */
    private $idClient;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_cat", type="string", length=30, nullable=false)
     */
    private $nomCat;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_jeu", type="string", length=30, nullable=false)
     */
    private $nomJeu;

    /**
     * @var int
     *
     * @ORM\Column(name="niv_diff", type="integer", nullable=false)
     */
    private $nivDiff;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdClient(): ?int
    {
        return $this->idClient;
    }

    public function setIdClient(int $idClient): self
    {
        $this->idClient = $idClient;

        return $this;
    }

    public function getNomCat(): ?string
    {
        return $this->nomCat;
    }

    public function setNomCat(string $nomCat): self
    {
        $this->nomCat = $nomCat;

        return $this;
    }

    public function getNomJeu(): ?string
    {
        return $this->nomJeu;
    }

    public function setNomJeu(string $nomJeu): self
    {
        $this->nomJeu = $nomJeu;

        return $this;
    }

    public function getNivDiff(): ?int
    {
        return $this->nivDiff;
    }

    public function setNivDiff(int $nivDiff): self
    {
        $this->nivDiff = $nivDiff;

        return $this;
    }


}
