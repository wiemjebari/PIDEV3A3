<?php

namespace App\Repository;

use App\Entity\User;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method User|null find($id, $lockMode = null, $lockVersion = null)
 * @method User|null findOneBy(array $criteria, array $orderBy = null)
 * @method User[]    findAll()
 * @method User[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class UserRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, User::class);
    }

    public function getallidclients(int $idcl): array
    {
        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery(
            '
            SELECT id_client FROM App\Entity\User u
            WHERE u.id_client > :thisone
            '
        )->setParameter('thisone', $idcl);
        // returns an array of Product objects
        return $query->getResult();
    }

    public function searchUser($criteria){
        
        return $this->createQueryBuilder('u')
            ->leftJoin('u.nom', 'na')
            ->where('na.nom = :userName')
            ->setParameter("userName", $criteria->getNom())
            ->getQuery()
            ->getResult()
        ;
    }

    public function findUserByNomAsc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT u FROM App\Entity\User u ORDER BY u.nom ASC');
                  return $query->getResult();
    }

    public function findUserByNomDesc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT u FROM App\Entity\User u ORDER BY u.nom DESC');
                  return $query->getResult();
    }

    public function findUserByPreomAsc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT u FROM App\Entity\User u ORDER BY u.prenom ASC');
                  return $query->getResult();
    }

    public function findUserByPreomDesc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT u FROM App\Entity\User u ORDER BY u.prenom DESC');
                  return $query->getResult();
    }

    public function findUserByGenreAsc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT u FROM App\Entity\User u ORDER BY u.sexe ASC');
                  return $query->getResult();
    }

    public function findUserByGenreDesc(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT u FROM App\Entity\User u ORDER BY u.sexe DESC');
                  return $query->getResult();
    }

    public function findAllDql(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT u FROM App\Entity\User u Where u.nom LIKE :nom')
                  ->setParameter('nom','%Nidhal');
        return $query->getResult();
    }

    public function numberOfUsers(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(u) FROM App\Entity\User u');
        return $query->getSingleScalarResult();
    }

    public function numberOfUsersGenre1(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(u) FROM App\Entity\User u where u.sexe LIKE :nom')
                  ->setParameter('nom','%Femme');
        return $query->getSingleScalarResult();
    }
    
    public function numberOfUsersGenre2(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(u) FROM App\Entity\User u where u.sexe LIKE :nom')
                  ->setParameter('nom','%Homme');
        return $query->getSingleScalarResult();
    }

    public function numberOfUserByRole1(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(u) FROM App\Entity\User u Where u.role LIKE :role1')
                  ->setParameter('role1','%Admin');
                  return $query->getSingleScalarResult();
    }

    public function numberOfUserByRole2(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
                  ->createQuery('SELECT count(u) FROM App\Entity\User u Where u.role LIKE :role1')
                  ->setParameter('role1','%Client');
                  return $query->getSingleScalarResult();
    }
    

   // i connect?yes
    // /**
    //  * @return User[] Returns an array of User objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('u')
            ->andWhere('u.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('u.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?User
    {
        return $this->createQueryBuilder('u')
            ->andWhere('u.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
