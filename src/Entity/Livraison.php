<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Livraison
 *
 * @ORM\Table(name="livraison")
 * @ORM\Entity
 */
class Livraison
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_livraison", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idLivraison;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse_depart", type="string", length=255, nullable=false)
     */
    private $adresseDepart;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse_arrive", type="string", length=255, nullable=false)
     */
    private $adresseArrive;

    /**
     * @var string
     *
     * @ORM\Column(name="photo_produit", type="string", length=255, nullable=false)
     */
    private $photoProduit;

    /**
     * @var float
     *
     * @ORM\Column(name="prix_produit", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixProduit;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255, nullable=false)
     */
    private $etat;

    /**
     * @var int
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false)
     */
    private $idUser;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_reception", type="date", nullable=false)
     */
    private $dateReception;

    public function getIdLivraison(): ?int
    {
        return $this->idLivraison;
    }

    public function getAdresseDepart(): ?string
    {
        return $this->adresseDepart;
    }

    public function setAdresseDepart(string $adresseDepart): self
    {
        $this->adresseDepart = $adresseDepart;

        return $this;
    }

    public function getAdresseArrive(): ?string
    {
        return $this->adresseArrive;
    }

    public function setAdresseArrive(string $adresseArrive): self
    {
        $this->adresseArrive = $adresseArrive;

        return $this;
    }

    public function getPhotoProduit(): ?string
    {
        return $this->photoProduit;
    }

    public function setPhotoProduit(string $photoProduit): self
    {
        $this->photoProduit = $photoProduit;

        return $this;
    }

    public function getPrixProduit(): ?float
    {
        return $this->prixProduit;
    }

    public function setPrixProduit(float $prixProduit): self
    {
        $this->prixProduit = $prixProduit;

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

    public function getIdUser(): ?int
    {
        return $this->idUser;
    }

    public function setIdUser(int $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }

    public function getDateReception(): ?\DateTimeInterface
    {
        return $this->dateReception;
    }

    public function setDateReception(\DateTimeInterface $dateReception): self
    {
        $this->dateReception = $dateReception;

        return $this;
    }


}
