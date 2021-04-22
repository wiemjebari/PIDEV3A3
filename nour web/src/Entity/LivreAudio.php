<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * LivreAudio
 *
 * @ORM\Table(name="livre_audio", indexes={@ORM\Index(name="categorie_id", columns={"categorie_id"})})
 * @ORM\Entity
 */
class LivreAudio
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
     * @ORM\Column(name="titre", type="string", length=255, nullable=false)
     * @Assert\Length(
     *      min = 2,
     *      max = 10,
     *      minMessage = "le titre doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "le titre ne peut pas comporter plus de {{ limit }} caractères"
     * )
     */
    private $titre;

    /**
     * @var string
     *
     * @ORM\Column(name="editeur", type="string", length=255, nullable=false)
     * @Assert\Length(
     *      min = 2,
     *      max = 10,
     *      minMessage = "le nom de l'éditeur doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "le nom de l'éditeur ne peut pas comporter plus de {{ limit }} caractères"
     * )
     */
    private $editeur;

    /**
     * @var int
     *
     * @ORM\Column(name="duree", type="integer", nullable=false)
     * @Assert\Positive
     */
    private $duree;

    /**
     * @var string
     *
     * @ORM\Column(name="source", type="string", length=255, nullable=false)
     * @Assert\Length(
     *      min = 2,
     *      max = 20,
     *      minMessage = "la source doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "la source ne peut pas comporter plus de {{ limit }} caractères"
     * )
     */
    private $source;

    /**
     * @var string
     *
     * @ORM\Column(name="audio", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="Veuillez ajouter votre fichier")
     */
    private $audio;

    /**

     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Veuillez ajouter votre image")
     * @Assert\File(mimeTypes={ "image/jpeg" })

     */
    private $image;

    /**
     * @var \CategorieAudio
     *
     * @ORM\ManyToOne(targetEntity="CategorieAudio")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="categorie_id", referencedColumnName="id")
     * })
     */
    private $categorie;



    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

        return $this;
    }

    public function getEditeur(): ?string
    {
        return $this->editeur;
    }

    public function setEditeur(string $editeur): self
    {
        $this->editeur = $editeur;

        return $this;
    }

    public function getDuree(): ?int
    {
        return $this->duree;
    }

    public function setDuree(int $duree): self
    {
        $this->duree = $duree;

        return $this;
    }

    public function getSource(): ?string
    {
        return $this->source;
    }

    public function setSource(string $source): self
    {
        $this->source = $source;

        return $this;
    }

    public function getAudio()
    {
        return $this->audio;
    }

    public function setAudio( $audio): self
    {
        $this->audio = $audio;

        return $this;
    }

    public function getImage()
    {
        return $this->image;
    }

    public function setImage( $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getCategorie(): ?CategorieAudio
    {
        return $this->categorie;
    }

    public function setCategorie(?CategorieAudio $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }




}
