<?php

namespace App\Repository;

use App\Entity\Randonnees;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;


/**
 * @method Randonnees|null find($id, $lockMode = null, $lockVersion = null)
 * @method Randonnees|null findOneBy(array $criteria, array $orderBy = null)
 * @method Randonnees[]    findAll()
 * @method Randonnees[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class RandonneeRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Randonnees::class);
    }

    /**
     * @return Randonnees[] Returns an array of Randonnee objects
     */

    public function findEntitiesByString($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.lieu like :val')
            ->setParameter('val', $value.'%')
            ->orderBy('c.prix', 'DESC')
            ->getQuery()
            ->getResult()
        ;
    }

    /**
     * @return Randonnees[] Returns an array of Randonnee objects
     */
    public function OrderByDate(){

            return $this->createQueryBuilder('r')
                ->orderBy( 'r.date', 'ASC')
                ->getQuery()->getResult();

    }

    /**
     * @return Randonnees[] Returns an array of Randonnee objects
     */

    public function filterParPrix($prix)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.prix < :val')
            ->setParameter('val', $prix)
            ->orderBy('c.prix', 'DESC')
            ->getQuery()
            ->getResult()
            ;
    }


    /*
    public function findOneBySomeField($value): ?Randonnee
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */


}
