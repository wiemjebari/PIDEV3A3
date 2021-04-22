<?php


namespace App\Repository;


use App\Entity\LivreAudio;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method LivreAudio|null find($id, $lockMode = null, $lockVersion = null)
 * @method LivreAudio|null findOneBy(array $criteria, array $orderBy = null)
 * @method LivreAudio[]    findAll()
 * @method LivreAudio[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class LivreAudioRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, LivreAudio::class);
    }
    public function search($term)
    {
        return $this->createQueryBuilder('LivreAudio')
            ->andWhere('livreAudio.titre LIKE :searchTerm OR cat.iconKey LIKE :searchTerm')
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