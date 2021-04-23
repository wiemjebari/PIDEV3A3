<?php
//
//namespace App\Entity;
//
//use Doctrine\ORM\Mapping as ORM;
//
///**
// * Reaction
// *
// * @ORM\Table(name="reaction", indexes={@ORM\Index(name="FK56", columns={"idUser"}), @ORM\Index(name="FK567", columns={"idArticle"})})
// * @ORM\Entity
// */
//class Reaction
//{
//    /**
//     * @var int
//     *
//     * @ORM\Column(name="idReaction", type="integer", nullable=false)
//     * @ORM\Id
//     * @ORM\GeneratedValue(strategy="IDENTITY")
//     */
//    private $idreaction;
//
//    /**
//     * @var int
//     *
//     * @ORM\Column(name="type", type="integer", nullable=false)
//     */
//    private $type;
//
//    /**
//     * @var \User
//     *
//     * @ORM\ManyToOne(targetEntity="User")
//     * @ORM\JoinColumns({
//     *   @ORM\JoinColumn(name="idUser", referencedColumnName="idUser")
//     * })
//     */
//    private $iduser;
//
//    /**
//     * @var \Conseils
//     *
//     * @ORM\ManyToOne(targetEntity="Conseils")
//     * @ORM\JoinColumns({
//     *   @ORM\JoinColumn(name="idArticle", referencedColumnName="id_article")
//     * })
//     */
//    private $idarticle;
//
//
//}
