<?php


namespace App\Repository;

use App\Entity\SearchData;
use App\Entity\Produit;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\QueryBuilder;
use Doctrine\Persistence\ManagerRegistry;
use Doctrine\Common\Collections\ArrayCollection;
use Knp\Component\Pager\Pagination\PaginationInterface;
use Knp\Component\Pager\PaginatorInterface;


class ProduitRepository extends ServiceEntityRepository
{
    /**
     * @method Produit|null find($idc, $lockMode = null, $lockVersion = null)
     * @method Produit|null findOneBy(array $criteria, array $orderBy = null)
     * @method Produit[]    findAll()
     * @method Produit[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
     */
private $paginator;

    public function __construct(ManagerRegistry $registry,PaginatorInterface $paginator)
    {
        parent::__construct($registry, Produit::class);
        $this-> paginator= $paginator;
    }

    /**
     * @return PaginationInterface
     */

 public function findSearch(SearchData $search):PaginationInterface
 {

    $query=$this
        ->createQueryBuilder('p')
        ->select('c','p')
        ->join('p.idcategorie','c');

     if (!empty($search->q)){
         $query= $query
             ->andWhere('p.nomproduit LIKE :q')
             ->setParameter('q',"%{$search->q}%");
     }

     if (!empty($search->min)){

         $query =$query
             ->andWhere('p.prixproduit >= :min')
             ->setParameter('min',$search->min);
     }

     if (!empty($search->max)){

         $query =$query
             ->andWhere('p.prixproduit >= :max')
             ->setParameter('max',$search->max);
     }

     if (!empty($search->idcategorie)){

         $query =$query
             ->andWhere('c.idc IN (:idcategorie)')
             ->setParameter('idcategorie',$search->idcategorie);
     }
     $query = $query->getQuery();

     return $this->paginator->paginate($query,$search->page,2);
 }


}