<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints\DateTime;

/**
 * @ORM\Table(name="commentaire")
 * @ORM\Entity
 */
class Commentaire
{
    /**
     * @var int
     *
     * @ORM\Column(type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcomment;

    /**
     * @var string
     *
     * @ORM\Column(name="content", type="string", length=255, nullable=false)
     */
    private $content;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateCreation", type="date", nullable=false)
     */
    private $datecreation;



    /**
     * @var \Conseils
     *
     * @ORM\ManyToOne(targetEntity="Conseils")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idArticle", referencedColumnName="id_article")
     * })
     */
    private $idarticle;

    /**
     * @ORM\Column(type="integer")
     */
    private $idUser;


    public function __construct()
    {
        $this->setDatecreation(new \DateTime('now'));
    }

    public function getIdcomment(): ?int
    {
        return $this->idcomment;
    }

    public function getContent(): ?string
    {
        return $this->content;
    }

    public function setContent(string $content): self
    {
        $this->content = $content;

        return $this;
    }

    public function getDatecreation(): ?\DateTimeInterface
    {
        return $this->datecreation;
    }

    public function setDatecreation(\DateTimeInterface $datecreation): self
    {
        $this->datecreation = $datecreation;

        return $this;
    }



    public function getIdarticle(): ?Conseils
    {
        return $this->idarticle;
    }

    public function setIdarticle(?Conseils $idarticle): self
    {
        $this->idarticle = $idarticle;

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


}
