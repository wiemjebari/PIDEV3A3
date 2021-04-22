<?php


namespace App\Repository;
use App\Entity\CategorieAudio;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method CategorieAudio|null find($id, $lockMode = null, $lockVersion = null)
 * @method CategorieAudio|null findOneBy(array $criteria, array $orderBy = null)
 * @method CategorieAudio[]    findAll()
 * @method CategorieAudio[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */

class CategorieAudioRepository extends ServiceEntityRepository

{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, CategorieAudio::class);
    }
    public function search($term)
    {
        return $this->createQueryBuilder('CategorieAudio')
            ->andWhere('categorieAudio.titre LIKE :searchTerm OR cat.iconKey LIKE :searchTerm')
            ->setParameter('titre', '%'.$term.'%')
            ->getQuery()
            ->execute();
    }
    // /**
    //  * @return Produit[] Returns an array of Produit objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Produit
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}