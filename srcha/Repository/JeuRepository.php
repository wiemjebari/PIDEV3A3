<?php

namespace App\Repository;

use App\Entity\Jeu;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Jeu|null find($id, $lockMode = null, $lockVersion = null)
 * @method Jeu|null findOneBy(array $criteria, array $orderBy = null)
 * @method Jeu[]    findAll()
 * @method Jeu[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class JeuRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Jeu::class);
    }

    public function findJeuByIdclient($idc){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT j FROM App\Entity\Jeu j Where j.id_client like :th')
                  ->setParameter('th',$idc);
                  return $query->getResult();
    }

    public function findJeuByNamejeuAsc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT j FROM App\Entity\Jeu j ORDER BY j.nom_jeu ASC');
                  return $query->getResult();
    }

    public function findJeuByNamejeuDesc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT j FROM App\Entity\Jeu j ORDER BY j.nom_jeu DESC');
                  return $query->getResult();
    }

    public function findJeuByNamecatAsc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT j FROM App\Entity\Jeu j ORDER BY j.nom_cat ASC');
                  return $query->getResult();
    }

    public function findJeuByNamecatDesc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT j FROM App\Entity\Jeu j ORDER BY j.nom_cat DESC');
                  return $query->getResult();
    }

    public function findJeuByNivdiffAsc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT j FROM App\Entity\Jeu j ORDER BY j.niv_diff ASC');
                  return $query->getResult();
    }

    public function findJeuByNivdiffDesc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT j FROM App\Entity\Jeu j ORDER BY j.niv_diff DESC');
                  return $query->getResult();
    }

    public function findAllDql(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT j FROM App\Entity\Jeu j Where j.nom_jeu LIKE :nom')
                  ->setParameter('nom','%Snake');
        return $query->getResult();
    }

    public function numberOfJeux(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(j) FROM App\Entity\Jeu j');
        return $query->getSingleScalarResult();
    }

    public function numberOfJeuxdiff1(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(j) FROM App\Entity\Jeu j where j.niv_diff=1');
        return $query->getSingleScalarResult();
    }

    public function numberOfJeuxdiff2(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(j) FROM App\Entity\Jeu j where j.niv_diff=2');
        return $query->getSingleScalarResult();
    }

    public function numberOfJeuxdiff3(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(j) FROM App\Entity\Jeu j where j.niv_diff=3');
        return $query->getSingleScalarResult();
    }

    public function numberOfJeuxnom1(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(j) FROM App\Entity\Jeu j where j.nom_jeu LIKE :nom')
                  ->setParameter('nom','%Ping-Pong');
        return $query->getSingleScalarResult();
    }

    public function numberOfJeuxnom2(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(j) FROM App\Entity\Jeu j where j.nom_jeu LIKE :nom')
                  ->setParameter('nom','%Snake');
        return $query->getSingleScalarResult();
    }

    public function numberOfJeuxcat1(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(j) FROM App\Entity\Jeu j where j.nom_cat LIKE :nom')
                  ->setParameter('nom','%Meditation');
        return $query->getSingleScalarResult();
    }

    public function numberOfJeuxcat2(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(j) FROM App\Entity\Jeu j where j.nom_cat LIKE :nom')
                  ->setParameter('nom','%Adventure');
        return $query->getSingleScalarResult();
    }

    public function numberOfJeuxcat3(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(j) FROM App\Entity\Jeu j where j.nom_cat LIKE :nom')
                  ->setParameter('nom','%Yoga');
        return $query->getSingleScalarResult();
    }

    // /**
    //  * @return Jeu[] Returns an array of Jeu objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('j')
            ->andWhere('j.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('j.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Jeu
    {
        return $this->createQueryBuilder('j')
            ->andWhere('j.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
