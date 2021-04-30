<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\QueryBuilder;

/**
 * Produit
 *
 * @ORM\Table(name="produit", indexes={@ORM\Index(name="idcategorie", columns={"idcategorie"})})
 * @ORM\Entity
 */
class Produit
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
     * @var string
     *
     * @ORM\Column(name="nomproduit", type="string", length=255, nullable=false)
     */
    private $nomproduit;

    /**
     * @var float
     *Assert/NotEqualTo(
     * value=0,
     * message="le prix d'un produit ne doit pas etre égal à 0"
     * )
     * @ORM\Column(name="prixproduit", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixproduit;

    /**
     * @var int
     * * @Assert\Positive
     * @ORM\Column(name="stockproduit", type="integer", nullable=false)
     */
    private $stockproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="Please, upload the file")
     * @Assert\File(mimeTypes={ "image/jpeg" })

     */
    private $photo;

    /**
     * @var \Categorie
     *
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumns({
     * @ORM\JoinColumn(name="idcategorie", referencedColumnName="idc")
     * })
     */
    private $idcategorie;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomproduit(): ?string
    {
        return $this->nomproduit;
    }

    public function setNomproduit(string $nomproduit): self
    {
        $this->nomproduit = $nomproduit;

        return $this;
    }

    public function getPrixproduit(): ?float
    {
        return $this->prixproduit;
    }

    public function setPrixproduit(float $prixproduit): self
    {
        $this->prixproduit = $prixproduit;

        return $this;
    }

    public function getStockproduit(): ?int
    {
        return $this->stockproduit;
    }

    public function setStockproduit(int $stockproduit): self
    {
        $this->stockproduit = $stockproduit;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getPhoto()
    {
        return $this->photo;
    }

    public function setPhoto($photo): self
    {
        $this->photo = $photo;

        return $this;
    }

    public function getIdcategorie(): ?Categorie
    {
        return $this->idcategorie;
    }

    public function setIdcategorie(?Categorie $idcategorie): self
    {
        $this->idcategorie = $idcategorie;

        return $this;
    }




    /**
     * @inheritDoc
     */
    public function jsonSerialize()
    {
        return get_object_vars($this);
    }
}
