<?php

namespace App\Repository;

use App\Entity\Reservationrandonnees;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Reservationrandonnees|null find($id, $lockMode = null, $lockVersion = null)
 * @method Reservationrandonnees|null findOneBy(array $criteria, array $orderBy = null)
 * @method Reservationrandonnees[]    findAll()
 * @method Reservationrandonnees[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ReservationrandonneesRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Reservationrandonnees::class);
    }

    // /**
    //  * @return Reservationrandonnees[] Returns an array of Reservationrandonnees objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Reservationrandonnees
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */


}
