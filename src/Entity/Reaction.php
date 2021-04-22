<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reaction
 *
 * @ORM\Table(name="reaction", indexes={@ORM\Index(name="FK567", columns={"idArticle"}), @ORM\Index(name="FK56", columns={"idUser"})})
 * @ORM\Entity
 */
class Reaction
{
    /**
     * @var int
     *
     * @ORM\Column(name="idReaction", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idreaction;

    /**
     * @var int
     *
     * @ORM\Column(name="idUser", type="integer", nullable=false)
     */
    private $iduser;

    /**
     * @var int
     *
     * @ORM\Column(name="idArticle", type="integer", nullable=false)
     */
    private $idarticle;

    /**
     * @var int
     *
     * @ORM\Column(name="type", type="integer", nullable=false)
     */
    private $type;

    public function getIdreaction(): ?int
    {
        return $this->idreaction;
    }

    public function getIduser(): ?int
    {
        return $this->iduser;
    }

    public function setIduser(int $iduser): self
    {
        $this->iduser = $iduser;

        return $this;
    }

    public function getIdarticle(): ?int
    {
        return $this->idarticle;
    }

    public function setIdarticle(int $idarticle): self
    {
        $this->idarticle = $idarticle;

        return $this;
    }

    public function getType(): ?int
    {
        return $this->type;
    }

    public function setType(int $type): self
    {
        $this->type = $type;

        return $this;
    }


}
