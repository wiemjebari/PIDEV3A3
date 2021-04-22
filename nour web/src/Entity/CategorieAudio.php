<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints as Assert;



/**
 * CategorieAudio
 *
 * @ORM\Table(name="categorie_audio")
 * @ORM\Entity
 */
class CategorieAudio
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
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     * @Assert\Length(
     *      min = 2,
     *      max = 50,
     *      minMessage = "le nom doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "le nom ne peut pas comporter plus de {{ limit }} caractères"
     * )
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     * @Assert\Length(
     *      min = 2,
     *      max = 100,
     *      minMessage = "le nom doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "le nom ne peut pas comporter plus de {{ limit }} caractères"
     * )
     */
    private $description;


    /**
     * @ORM\OneToMany(targetEntity=LivreAudio::class, mappedBy="categorieAudio")
     */
    private $LivreAudio;

    public function __construct()
    {
        $this->LivreAudio = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

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


    public function __toString()
    {
        return $this->getNom();
    }

    /**
     * @return Collection|LivreAudio[]
     */
    public function getLivreAudio(): Collection
    {
        return $this->LivreAudio;
    }

    public function addLivreAudio(LivreAudio $livreAudio): self
    {
        if (!$this->LivreAudio->contains($livreAudio)) {
            $this->LivreAudio[] = $livreAudio;
            $livreAudio->setCategorie($this);
        }

        return $this;
    }

    public function removeLivreAudio(LivreAudio $livreAudio): self
    {
        if ($this->LivreAudio->removeElement($livreAudio)) {
            // set the owning side to null (unless already changed)
            if ($livreAudio->getCategorie() === $this) {
                $livreAudio->setCategorie(null);
            }
        }

        return $this;
    }

}
