<?php


namespace App\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\Collection;
use App\Entity\Categorie;
use Doctrine\ORM\QueryBuilder;

/**
 * @ORM\Entity(repositoryClass=ProduitRepository::class)
 */
/**
 * SearchData
 *
 * @ORM\Table(name="searchdata")
 * @ORM\Entity
 */
class SearchData
{
    /**
     * @var int
     */
    public $page=1;

    /**
     * @var string
     */
    public $q='';

    /**
     * @var Categorie
     */
    public $categories=[];
    /**
     * @var null|integer
     */
    public $max;

    /**
     * @var null|integer
     */
    public $min;

    /**
     * @var boolean
     */
    public $promo=false;
}