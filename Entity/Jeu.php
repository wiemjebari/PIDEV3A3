<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Jeu
 *
 * @ORM\Table(name="jeu")
 * @ORM\Entity
 */
class Jeu
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
     * @ORM\Column(name="id_client", type="integer", nullable=false)
     */
    private $idClient;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_cat", type="string", length=255, nullable=false)
     */
    private $nomCat;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_jeu", type="string", length=255, nullable=false)
     */
    private $nomJeu;

    /**
     * @var int
     *
     * @ORM\Column(name="niv_diff", type="integer", nullable=false)
     */
    private $nivDiff;


}
