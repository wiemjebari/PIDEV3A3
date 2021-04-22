<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commandeprod
 *
 * @ORM\Table(name="commandeprod")
 * @ORM\Entity
 */
class Commandeprod
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
     * @ORM\Column(name="id_acheteur", type="integer", nullable=false)
     */
    private $idAcheteur;

    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;

    /**
     * @var int
     *
     * @ORM\Column(name="id_produit", type="integer", nullable=false)
     */
    private $idProduit;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdAcheteur(): ?int
    {
        return $this->idAcheteur;
    }

    public function setIdAcheteur(int $idAcheteur): self
    {
        $this->idAcheteur = $idAcheteur;

        return $this;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getIdProduit(): ?int
    {
        return $this->idProduit;
    }

    public function setIdProduit(int $idProduit): self
    {
        $this->idProduit = $idProduit;

        return $this;
    }


}
