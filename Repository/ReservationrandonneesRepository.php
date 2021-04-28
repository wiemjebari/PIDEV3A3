<?php

namespace App\Repository;

use App\Entity\Reservationrandonnees;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use Twilio\Rest\Client;

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


    public  function sms(){



// Your Account SID and Auth Token from twilio.com/console
        $account_sid = 'AC9e096a12d4c5f524f01957bed6d64c6b';
        $auth_token = 'e3ba46cf8a3a61c3197eb52ca158395e';
// In production, these should be environment variables. E.g.:
// $auth_token = $_ENV["TWILIO_AUTH_TOKEN"]

// A Twilio number you own with SMS capabilities
        $twilio_number = "+14157120486";

        $client = new Client($account_sid, $auth_token);
        $client->messages->create(
// Where to send a text message (your cell phone?)
            '+21655967666',
            array(
                'from' => $twilio_number,
                'body' => 'You have a new project proposal'
            )
        );

    }


}
