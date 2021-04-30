<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\CategorieRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

use Gedmo\Mapping\Annotation as Gedmo;

/**
 * @ORM\Entity(repositoryClass=CategorieRepository::class)
 */
/**
 * Categorie
 *
 * @ORM\Table(name="categorie")
 * @ORM\Entity
 */
class Categorie
{
    /**
     * @var int
     *
     * @ORM\Column(name="idc", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idc;


    /**
     * @var string
     *
     * @ORM\Column(name="nomcategorie", type="string", length=255, nullable=false)
     */
    private $nomcategorie;


    public function __construct()
    {
        $this->categories = new ArrayCollection();

        $this->produits = new ArrayCollection();
    }



    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=255, nullable=false)
     */
    private $adresse;
    /**
     * @ORM\OneToMany(targetEntity=Categorie::class, mappedBy="produit")
     */
    private $categories;

    /**
     * @ORM\OneToMany(targetEntity=Produit::class, mappedBy="categories")
     */
    private $produits;

    /**
     * @ORM\Column(type="string", length=7, nullable=true)
     */
    private $color;




    public function getIdc(): ?int
    {
        return $this->idc;
    }

    public function getNomcategorie(): ?string
    {
        return $this->nomcategorie;
    }

    public function setNomcategorie(string $nomcategorie): self
    {
        $this->nomcategorie = $nomcategorie;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }
    public function getParent(): ?self
    {
        return $this->parent;
    }

    public function setParent(?self $parent): self
    {
        $this->parent = $parent;

        return $this;
    }
    public function __toString()
    {
        return $this->getNomcategorie();
    }
    /**
     * @return Collection|Produit[]
     */
    public function getProduits(): Collection
    {
        return $this->produits;
    }
    /**
     * @return Collection|self[]
     */
    public function getCategories(): Collection
    {
        return $this->categories;
    }

    public function addCategory(self $category): self
    {
        if (!$this->categories->contains($category)) {
            $this->categories[] = $category;
            $category->setParent($this);
        }

        return $this;
    }

    public function removeCategory(self $category): self
    {
        if ($this->categories->contains($category)) {
            $this->categories->removeElement($category);
            // set the owning side to null (unless already changed)
            if ($category->getParent() === $this) {
                $category->setParent(null);
            }
        }

        return $this;
    }

    public function addProduit(Produit $produit): self
    {
        if (!$this->produits->contains($produit)) {
            $this->produits[] = $produit;
            $produit->setCategorie($this);
        }

        return $this;
    }

    public function removeProduit(Produit $produit): self
    {
        if ($this->produits->contains($produit)) {
            $this->produits->removeElement($produit);
            // set the owning side to null (unless already changed)
            if ($produit->getCategorie() === $this) {
                $produit->setCategorie(null);
            }
        }

        return $this;
    }
    public function getColor(): ?string
    {
        return $this->color;
    }


    public function setColor(?string $color): self
    {
        $this->color = $color;

        return $this;
    }


}
