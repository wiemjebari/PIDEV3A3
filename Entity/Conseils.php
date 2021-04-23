<?php

namespace App\Entity;

use App\Repository\ConseilsRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ConseilsRepository::class)
 */
class Conseils
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id_article;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Titre_article;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $image;

    public function getIdArticle(): ?int
    {
        return $this->id_article;
    }

    public function setIdArticle($id_article): void
    {
        $this->id_article = $id_article;
    }

    public function getTitreArticle(): ?string
    {
        return $this->Titre_article;
    }

    public function setTitreArticle(string $Titre_article): self
    {
        $this->Titre_article = $Titre_article;

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

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }
}