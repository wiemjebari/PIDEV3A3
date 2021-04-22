<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Produitfavories
 *
 * @ORM\Table(name="produitfavories")
 * @ORM\Entity
 */
class Produitfavories
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_favori", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idFavori;

    /**
     * @var int
     *
     * @ORM\Column(name="id_produit", type="integer", nullable=false)
     */
    private $idProduit;

    /**
     * @var int
     *
     * @ORM\Column(name="id_owner", type="integer", nullable=false)
     */
    private $idOwner;

    public function getIdFavori(): ?int
    {
        return $this->idFavori;
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

    public function getIdOwner(): ?int
    {
        return $this->idOwner;
    }

    public function setIdOwner(int $idOwner): self
    {
        $this->idOwner = $idOwner;

        return $this;
    }


}
