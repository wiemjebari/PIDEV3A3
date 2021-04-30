<?php

namespace App\Repository;

use App\Entity\Centre;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;

use Doctrine\Persistence\ManagerRegistry;
/**
 * @method Centre|null find($id, $lockMode = null, $lockVersion = null)
 * @method Centre|null findOneBy(array $criteria, array $orderBy = null)
 * @method Centre[]    findAll()
 * @method Centre[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CentreRepository extends ServiceEntityRepository
{

    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Centre::class);
        
    }
   public function OrderByMail()

    {
        $em=$this->getEntityManager();
        $query=$em->createQuery('
        select s from APP\Entity\Centre s order by s.eMail ASC');
        return $query->getResult();

        
    }
    
    /**
     * @return Centres[] Returns an array of Centre objects
     */

    public function findEntitiesByString($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.nomCentre like :val')
            ->setParameter('val', $value.'%')
           
            ->getQuery()
            ->getResult()
        ;
    }
    
    
     
  

}